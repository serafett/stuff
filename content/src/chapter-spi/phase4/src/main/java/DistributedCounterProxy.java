import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.spi.AbstractOperation;
import com.hazelcast.spi.InvocationBuilder;
import com.hazelcast.spi.NodeEngine;
import com.hazelcast.spi.PartitionAwareOperation;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DistributedCounterProxy implements DistributedCounter {
    private final NodeEngine nodeEngine;
    private final String objectId;

    public DistributedCounterProxy(String objectId, NodeEngine nodeEngine) {
        this.nodeEngine = nodeEngine;
        this.objectId = objectId;
    }

    public Object getId() {
        return objectId;
    }

    public String getName() {
        return null;
    }

    public int inc(int amount) {
        IncOperation operation = new IncOperation(objectId, amount);
        int partitionId = nodeEngine.getPartitionService().getPartitionId(objectId);
        InvocationBuilder builder = nodeEngine.getOperationService()
                .createInvocationBuilder("DistributedCounterService", operation, partitionId);
        try {
            return (Integer) builder.build().invoke().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

      static class IncOperation extends AbstractOperation implements PartitionAwareOperation {
        private String objectId;
        private int amount, returnValue;

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
            DistributedCounterService service = getService();
            System.out.println("Executing "+objectId+".inc() on: "+getNodeEngine().getThisAddress());
            returnValue = service.containers[getPartitionId()].inc(objectId, amount);
        }

        public boolean returnsResponse() {
            return true;
        }

        public Object getResponse() {
            return returnValue;
        }
    }

    public void destroy() {
    }
}
