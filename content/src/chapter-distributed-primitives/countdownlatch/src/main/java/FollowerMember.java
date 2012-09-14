import com.hazelcast.core.*;
public class FollowerMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        ICountDownLatch latch = hzInstance.getCountDownLatch("countDownLatch");
        System.out.println("Waiting");
        latch.await();
        System.out.println("Complete!");
    }
}