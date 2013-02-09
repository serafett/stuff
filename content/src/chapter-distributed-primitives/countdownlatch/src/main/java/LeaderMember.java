import com.hazelcast.core.*;
public class LeaderMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        ICountDownLatch latch = hzInstance.getCountDownLatch("countDownLatch");
        System.out.println("Starting");
        //we init the latch with 1, since we only need to complete a single step.
         latch.trySetCount(1);
        //do some sleeping to simulate doing something    
        Thread.sleep(30000);
        //now we do a countdown which notifies all followers
        latch.countDown();
        System.out.println("Leader finished");
        //we need to clean up the latch
        latch.destroy();
    }
}