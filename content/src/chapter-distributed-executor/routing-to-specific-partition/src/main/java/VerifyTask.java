import com.hazelcast.core.*;
import java.io.Serializable;
public class VerifyTask implements
        Runnable, Serializable, HazelcastInstanceAware {
    private final String key;
    private transient HazelcastInstance hzInstance;
    public VerifyTask(String key) {
        this.key = key;
    }
    public void setHazelcastInstance(HazelcastInstance hzInstance) {
        this.hzInstance = hzInstance;
    }
    public void run() {
        IMap map = hzInstance.getMap("map");
        boolean localKey = map.localKeySet().contains(key);
        System.out.println("Key is local:" + localKey);
    }
}
