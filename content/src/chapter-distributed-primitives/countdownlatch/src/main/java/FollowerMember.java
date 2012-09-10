import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICountDownLatch;

public class FollowerMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        ICountDownLatch latch = hazelcastInstance.getCountDownLatch("countDownLatch");
        System.out.println("Waiting");
        latch.await();
        System.out.println("Complete!");
    }
}