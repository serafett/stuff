import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Set;

public class WriteNode {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        Set<String> set = hazelcastInstance.getSet("set");
        set.add("Tokyo");
        set.add("Paris");
        set.add("London");
        set.add("New York");
        System.out.println("Putting finished");
    }
}
