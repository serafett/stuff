import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.BlockingQueue;

public class FullMember {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        BlockingQueue<String> queue = hz.getQueue("queue");
        for (; ; )
            System.out.println(queue.take());
    }
}
