import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.List;

public class ReadMember {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        List<String> list = hazelcastInstance.getList("list");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("Reading finished!");
    }
}
