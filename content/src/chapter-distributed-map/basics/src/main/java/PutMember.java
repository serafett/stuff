import com.hazelcast.core.*;
import java.util.Map;
public class PutMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        Map<String, String> map = hzInstance.getMap("map");
        map.put("1", "Tokyo");
    }
}
