import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

public class MasterNode {

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
