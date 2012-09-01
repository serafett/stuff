import com.hazelcast.core.PartitionAware;

import java.io.Serializable;

public class PartitionedTask implements Runnable, PartitionAware, Serializable {
    private final String partitionId;

    public PartitionedTask(String partitionId) {
        this.partitionId = partitionId;
    }

    @Override
    public void run() {
        System.out.println("echo");
    }

    @Override
    public Object getPartitionKey() {
        return partitionId;
    }
}
