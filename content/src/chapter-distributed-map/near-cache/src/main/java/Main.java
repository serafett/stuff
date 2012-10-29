import com.hazelcast.core.*;
import java.util.Map;
public class Main {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        Map<Long, Article> articles = hzInstance.getMap("articles");

     }
}
