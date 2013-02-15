import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.util.Map;
public class PortableMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        Map<String, Person> map = hzInstance.getMap("map");
        map.put("foo", new Person("foo"));
    }
}
