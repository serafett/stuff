import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.spi.AbstractOperation;

import java.io.IOException;

/**
* Created with IntelliJ IDEA.
* User: alarmnummer
* Date: 2/16/13
* Time: 5:13 PM
* To change this template use File | Settings | File Templates.
*/
class IncOperation extends AbstractOperation {
    private String objectId;
    private int amount,returnValue;
    public IncOperation(){}
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
        System.out.println("Executing "+objectId+".inc() on: "+getNodeEngine().getThisAddress());
        DistributedCounterService service = getService();
        // [mehmet: operation already knows its partition id]
        // int partitionId = getNodeEngine().getPartitionService().getPartitionId(objectId);
        int partitionId = getPartitionId();
        returnValue = service.containers[partitionId].inc(objectId, amount);
    }
    public boolean returnsResponse() {return true;}
    public Object getResponse() {return returnValue;}
}
