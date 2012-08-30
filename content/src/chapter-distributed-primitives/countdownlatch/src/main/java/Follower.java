import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.ICountDownLatch;

public class Follower {
    public static void main(String[] args) throws Exception {
        ICountDownLatch latch = Hazelcast.getCountDownLatch("countDownLatch");
        System.out.println("Waiting");
        latch.await();
        System.out.println("Complete!");
    }
}