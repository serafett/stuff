import com.hazelcast.core.*;
import java.io.Serializable;
import java.util.concurrent.Callable;
public class SumTask implements
        Callable<Integer>, Serializable, HazelcastInstanceAware {
    private transient HazelcastInstance hzInstance;
    public void setHazelcastInstance(HazelcastInstance hzInstance) {
        this.hzInstance = hzInstance;
    }
    public Integer call() throws Exception {
        System.out.println("hzInstance: "+hzInstance);
        IMap<String, Integer> map = hzInstance.getMap("map");
        int result = 0;
        for (String key : map.localKeySet()) {
            System.out.println("Calculating for key: " + key);
            result += map.get(key);
        }
        System.out.println("Local Result: " + result);
        return result;
    }
}
