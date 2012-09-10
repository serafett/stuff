import com.hazelcast.core.AtomicNumber;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;

public class RaceFreeMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        AtomicNumber number1 = hazelcastInstance.getAtomicNumber("number1");
        AtomicNumber number2 = hazelcastInstance.getAtomicNumber("number2");
        ILock lock = hazelcastInstance.getLock("lock");
        System.out.println("Started");
        for (int k = 0; k < 10000; k++) {
            if (k % 100 == 0) System.out.println("at: " + k);
            lock.lock();
            try {
                if (k % 2 == 0) {
                    long n1 = number1.get();
                    Thread.sleep(10);
                    long n2 = number2.get();
                    if (n1 - n2 != 0) System.out.println("Datarace detected!");
                } else {
                    number1.incrementAndGet();
                    number2.incrementAndGet();
                }
            } finally {
                lock.unlock();
            }
        }
        System.out.println("Finished");
    }
}