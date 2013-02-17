import com.hazelcast.spi.InvocationBuilder;
import com.hazelcast.spi.NodeEngine;

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

    public void destroy() {
    }
}
