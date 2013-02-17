import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.spi.AbstractOperation;

import java.io.IOException;

class IncOperation extends AbstractOperation {
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
        Container container = service.containers[getPartitionId()];
        returnValue = container.inc(objectId, amount);
    }

    public boolean returnsResponse() {
        return true;
    }

    public Object getResponse() {
        return returnValue;
    }
}
