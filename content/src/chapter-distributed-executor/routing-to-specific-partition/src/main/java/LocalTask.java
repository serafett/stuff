import com.hazelcast.core.*;
import java.io.Serializable;

public class LocalTask implements
        Runnable, PartitionAware, Serializable, HazelcastInstanceAware {
    private final String key;
    private transient HazelcastInstance hazelcastInstance;
    public LocalTask(String key) {
        this.key = key;
    }
    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }
    @Override
    public void run() {
        IMap map = hazelcastInstance.getMap("map");

        boolean localKey = map.localKeySet().contains(key);
        System.out.println("key is local:" + localKey);
    }
    @Override
    public Object getPartitionKey() {
        return key;
    }
}
