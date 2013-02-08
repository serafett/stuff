import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.spi.AbstractOperation;
import com.hazelcast.spi.InvocationBuilder;
import com.hazelcast.spi.NodeEngine;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DistributedCounterProxy implements DistributedCounter {
    private final NodeEngine nodeEngine;
    private final String objectId;
    public DistributedCounterProxy(String objectId, NodeEngine nodeEngine) {
        this.nodeEngine = nodeEngine;
        this.objectId = objectId;
    }
    public Object getId() {return objectId;}
    public String getName() {return null;}
    public int inc(int amount) {
        IncOperation operation = new IncOperation(objectId, amount);
        int partitionId = nodeEngine.getPartitionService().getPartitionId(objectId);
        InvocationBuilder builder = nodeEngine.getOperationService()
                .createInvocationBuilder("DistributedCounterService", operation, partitionId);
        try {
            final Future<Integer> future = builder.build().invoke();
            System.out.println("future.class:"+future.getClass());
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("ExecutionException or InterruptedException is thrown");
            throw new RuntimeException(e);
        }
    }
    static class IncOperation extends AbstractOperation {
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
            returnValue = 0;
            throw new IllegalStateException();
        }
        public boolean returnsResponse() {return true;}
        public Object getResponse() {return returnValue;}
    }
    public void destroy() {
    }
}
