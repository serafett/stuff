import com.hazelcast.core.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
public class MasterMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance defaultInstance = Hazelcast.getDefaultInstance();
        Map<String, String> map = defaultInstance.getMap("map");
        for (int k = 0; k < 10; k++) {
            map.put(UUID.randomUUID().toString(), "");
        }
        ExecutorService executor = defaultInstance.getExecutorService();
        for (String key : map.keySet()) {
            executor.execute(new LocalTask(key));
        }
    }
}
