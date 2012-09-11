import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.util.concurrent.BlockingQueue;
public class FullMember {
    public static void main(String[] args)throws Exception{
        Config config = new Config();
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        BlockingQueue<String> queue = hazelcastInstance.getQueue("queue");
        for(;;){
            String msg = queue.take();
            System.out.println("Received:"+msg);
        }
    }
}
