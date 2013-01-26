import com.hazelcast.core.*;
import java.util.Set;
public class ReadMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        Set<String> set = hzInstance.getSet("set");
        for (String s : set)
            System.out.println(s);
        System.out.println("Reading finished!");
    }
}
