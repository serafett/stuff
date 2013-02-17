import com.hazelcast.spi.InvocationBuilder;
import com.hazelcast.spi.NodeEngine;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
            final Future<Integer> future = builder.build().invoke();
            System.out.println("future.class:" + future.getClass());
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("ExecutionException or InterruptedException is thrown");
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}
