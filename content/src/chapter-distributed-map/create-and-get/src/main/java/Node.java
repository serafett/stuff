import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

public class Node {

    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        Map<String, String> map = hazelcastInstance.getMap("map");
        map.put("1", "Tokyo");
    }
}
