import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class Member {
    public static void main(String[] args) throws Exception {
        HazelcastInstance[] instances = new HazelcastInstance[3];
        for (int k = 0; k < instances.length; k++) {
            HazelcastInstance instance = Hazelcast.newHazelcastInstance();
            instances[k] = instance;
        }

        DistributedCounter[] counters = new DistributedCounter[20];
        for (int k = 0; k < counters.length; k++) {
            DistributedCounter counter = (DistributedCounter) instances[0].getDistributedObject("DistributedCounterService", "counter" + k);
            counters[k] = counter;
            System.out.println(counter.inc(1));
        }

        Thread.sleep(10000);

        System.out.println("Creating new members");


        for (int k = 0; k < 3; k++) {
            Hazelcast.newHazelcastInstance();
        }

        Thread.sleep(10000);

        for (int k = 0; k < counters.length; k++) {
            DistributedCounter counter = (DistributedCounter) instances[0].getDistributedObject("DistributedCounterService", "counter" + k);
            counters[k] = counter;
            System.out.println(counter.inc(1));
        }

        System.out.println("Finished");
    }
}
