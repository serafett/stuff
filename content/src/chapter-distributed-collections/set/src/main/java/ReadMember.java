import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.Set;

public class ReadMember {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        Set<String> set = hazelcastInstance.getSet("set");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("Reading finished!");
    }
}
