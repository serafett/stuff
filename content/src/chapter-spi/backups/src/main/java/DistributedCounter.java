import com.hazelcast.core.DistributedObject;

public interface DistributedCounter extends DistributedObject {
    int inc(int amount);
}
