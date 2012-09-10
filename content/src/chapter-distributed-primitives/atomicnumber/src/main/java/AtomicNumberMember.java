import com.hazelcast.core.*;
public class AtomicNumberMember {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        AtomicNumber counter = hazelcastInstance.getAtomicNumber("counter");
        for (int k = 0; k < 1000 * 1000; k++) {
            if (k % 200000 == 0) System.out.println("At: "+k);
            counter.incrementAndGet();
        }
        System.out.printf("Count is %s\n", counter.get());
    }
}