import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.BlockingQueue;

public class CollectionChangeNode {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        BlockingQueue<String> queue = hazelcastInstance.getQueue("queue");
        queue.put("foo");
        queue.put("bar");
        queue.take();
        queue.take();
    }
}
