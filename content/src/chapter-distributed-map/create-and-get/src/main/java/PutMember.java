import com.hazelcast.core.*;
import java.util.Map;
public class PutMember {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        Map<String, String> map = hazelcastInstance.getMap("map");
        map.put("1", "Tokyo");
    }
}
