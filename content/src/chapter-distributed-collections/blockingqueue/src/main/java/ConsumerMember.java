import com.hazelcast.core.*;
import java.util.concurrent.BlockingQueue;
public class ConsumerMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        BlockingQueue<Integer> queue = hzInstance.getQueue("queue");
        while (true){
            int item = queue.take();
            System.out.println("Consumed: " + item);
            if(item == -1){
                queue.put(-1);
                break;
            }
            Thread.sleep(5000);
        }
        System.out.println("Consumer Finished!");
    }
}