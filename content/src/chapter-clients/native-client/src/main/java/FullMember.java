import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import java.util.concurrent.BlockingQueue;
public class FullMember {
    public static void main(String[] args)throws Exception{
        Config config = new Config();
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        BlockingQueue<String> queue = hazelcastInstance.getQueue("queue");
        System.out.println(queue.take());
    }
}
