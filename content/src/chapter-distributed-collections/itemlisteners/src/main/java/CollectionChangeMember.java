import com.hazelcast.core.*;
import java.util.concurrent.BlockingQueue;
public class CollectionChangeMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        BlockingQueue<String> queue = hazelcastInstance.getQueue("queue");
        queue.put("foo");
        queue.put("bar");
        queue.take();
        queue.take();
    }
}
