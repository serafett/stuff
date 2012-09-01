import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IMap;
import com.hazelcast.core.PartitionAware;

import java.io.Serializable;

public class PartitionedTask implements
        Runnable, PartitionAware, Serializable, HazelcastInstanceAware {
    private final String partitionId;
    private HazelcastInstance hazelcastInstance;

    public PartitionedTask(String partitionId) {
        this.partitionId = partitionId;
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public void run() {
        IMap map =hazelcastInstance.getMap("map");

        boolean localKey = map.localKeySet().contains(partitionId);
        System.out.println("key is local:" + localKey);
    }

    @Override
    public Object getPartitionKey() {
        return partitionId;
    }
}
