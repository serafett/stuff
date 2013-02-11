import com.hazelcast.core.*;

import java.util.concurrent.TimeUnit;

public class FollowerMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        ICountDownLatch latch = hzInstance.getCountDownLatch("countDownLatch");
        System.out.println("name:"+latch.getName());
        System.out.println("id:"+latch.getId());
        System.out.println("Waiting");
        boolean success = latch.await(10, TimeUnit.SECONDS);
        System.out.println("Complete:"+success);
    }
}