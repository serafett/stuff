import com.hazelcast.core.Hazelcast;
import java.util.concurrent.BlockingQueue;
public class ProducerMain {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> queue = Hazelcast.getQueue("producerConsumerQueue");
        for (int k = 1; k < 1000; k++) {
            queue.put(k);
            System.out.println("Producing: " + k);
        }
        queue.put(-1);
        System.out.println("Producer Finished");
    }
}