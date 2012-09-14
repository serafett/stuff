import com.hazelcast.core.*;
import java.util.List;
public class ReadMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        List<String> list = hzInstance.getList("list");
        for (String s : list)
            System.out.println(s);
        System.out.println("Reading finished!");
    }
}
