import com.hazelcast.core.*;
import java.util.List;
public class WriteMember {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        List<String> list = hazelcastInstance.getList("list");
        list.add("Tokyo");
        list.add("Paris");
        list.add("London");
        list.add("New York");
        System.out.println("Putting finished!");
    }
}
