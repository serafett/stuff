import com.hazelcast.core.*;

import java.util.concurrent.TimeUnit;

public class Follower {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        ICountDownLatch latch = hz.getCountDownLatch("countDownLatch");
        System.out.println("Waiting");
        boolean success = latch.await(10, TimeUnit.SECONDS);
        System.out.println("Complete:"+success);
    }
}