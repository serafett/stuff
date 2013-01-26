import com.hazelcast.core.*;
import java.util.concurrent.BlockingQueue;
public class CollectionChangeMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        BlockingQueue<String> queue = hzInstance.getQueue("queue");
        queue.put("foo");
        queue.put("bar");
        queue.take();
        queue.take();
    }
}
