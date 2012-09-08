import com.hazelcast.core.Hazelcast;
import java.util.concurrent.BlockingQueue;
public class ConsumerNode {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> queue = Hazelcast.getQueue("queue");
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