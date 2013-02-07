import com.hazelcast.core.*;
public class Member {
    public static void main(String[] args) {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        DistributedCounter counter = (DistributedCounter)instance.getDistributedObject("DistributedCounterService","counter1");
        System.out.println(counter.inc(1));
        System.out.println(counter.inc(1));
    }
}
