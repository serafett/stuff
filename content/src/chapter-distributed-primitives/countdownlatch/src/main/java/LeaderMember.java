import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICountDownLatch;

public class LeaderMember {

    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        ICountDownLatch latch = hazelcastInstance.getCountDownLatch("countDownLatch");
        System.out.println("Starting");
        //we init the latch with 1, since we only need to complete a single step.
        latch.setCount(1);
        //do some sleeping to simulate doing something    
        Thread.sleep(30000);
        //now we do a countdown which opens the latch and all waiting
        //followers are notified.
        latch.countDown();
        System.out.println("Leader finished");
        //we need to clean up the latch
        latch.destroy();
    }
}