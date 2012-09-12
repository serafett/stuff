import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import java.util.concurrent.BlockingQueue;
public class LiteMember {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.setLiteMember(true);
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        BlockingQueue<String> queue = hazelcastInstance.getQueue("queue");
        queue.put("Hello!");
        System.out.println("Message send by lite member!");
    }
}
