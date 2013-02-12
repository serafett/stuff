import com.hazelcast.core.*;
import java.util.concurrent.BlockingQueue;
public class FullMember {
    public static void main(String[] args)throws Exception{
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        BlockingQueue<String> queue = hzInstance.getQueue("queue");
        for(;;)System.out.println(queue.take());
    }
}
