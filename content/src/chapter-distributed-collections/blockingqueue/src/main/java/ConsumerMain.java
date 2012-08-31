import com.hazelcast.core.Hazelcast;
import java.util.concurrent.BlockingQueue;
public class ConsumerMain {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> queue = Hazelcast.getQueue("producerConsumerQueue");
        while (true){
            Thread.sleep(1000);
            int item = queue.take();
            System.out.println("Consumed: " + item);
            if(item == -1){
                queue.put(-1);
                break;
            }           
        }
        System.out.println("Consumer Finished!");
    }
}