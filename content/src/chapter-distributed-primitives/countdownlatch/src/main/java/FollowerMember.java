import com.hazelcast.core.*;

import java.util.concurrent.TimeUnit;

public class FollowerMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        ICountDownLatch latch = hzInstance.getCountDownLatch("countDownLatch");
        System.out.println("Waiting");
        //todo:needs to be converted back to a no argument version
        latch.await(Long.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("Complete!");
    }
}