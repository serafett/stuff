import com.hazelcast.core.*;
import java.util.Map;
public class WithoutTransaction {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        Map map = hzInstance.getMap("map");
        try {
            map.put("1", "1");
            map.put("2", "2");
            throw new RuntimeException();
        } catch (RuntimeException e) {
        }
        System.out.println("Map.size: "+map.size());
    }
}
