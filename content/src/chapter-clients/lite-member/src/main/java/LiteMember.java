import com.hazelcast.core.*;
import java.util.concurrent.BlockingQueue;
public class LiteMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newLiteMemberHazelcastInstance();
        BlockingQueue<String> queue = hzInstance.getQueue("queue");
        queue.put("Hello!");
        System.out.println("Message send by lite member!");
    }
}
