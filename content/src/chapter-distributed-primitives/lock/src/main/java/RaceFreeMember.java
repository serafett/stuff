import com.hazelcast.core.*;
public class RaceFreeMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        AtomicNumber number1 = hzInstance.getAtomicNumber("number1");
        AtomicNumber number2 = hzInstance.getAtomicNumber("number2");
        ILock lock = hzInstance.getLock("lock");
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