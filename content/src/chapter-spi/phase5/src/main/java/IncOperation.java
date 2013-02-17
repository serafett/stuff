import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.spi.*;

import java.io.IOException;

class IncOperation extends AbstractOperation implements KeyBasedOperation,BackupAwareOperation {
    private String objectId;
    private int amount, returnValue;

    public IncOperation() {
    }

    public IncOperation(String objectId, int amount) {
        this.amount = amount;
        this.objectId = objectId;
    }

    protected void writeInternal(ObjectDataOutput out) throws IOException {
        super.writeInternal(out);
        out.writeUTF(objectId);
        out.writeInt(amount);
    }

    protected void readInternal(ObjectDataInput in) throws IOException {
        super.readInternal(in);
        objectId = in.readUTF();
        amount = in.readInt();
    }

    public void run() throws Exception {
        DistributedCounterService service = getService();
        System.out.println("Executing " + objectId + ".inc() on: " + getNodeEngine().getThisAddress());
        Container c = service.containers[getPartitionId()];
        returnValue = c.inc(objectId, amount);
    }

    public int getKeyHash() {
        return ("DistributedCounterService"+objectId).hashCode();
    }

    public int getAsyncBackupCount() {
        return 0;
    }

    public int getSyncBackupCount() {
        return 1;
    }

    public boolean shouldBackup() {
        return true;
    }

    public Operation getBackupOperation() {
        return new IncBackupOperation(objectId, amount);
    }

    public boolean returnsResponse() {
        return true;
    }

    public Object getResponse() {
        return returnValue;
    }
}
