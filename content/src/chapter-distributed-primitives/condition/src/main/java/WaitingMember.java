import com.hazelcast.core.*;

public class WaitingMember {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IAtomicLong counter = hz.getAtomicLong("counter");
        ILock lock = hz.getLock("lock");

        System.out.println("Starting wait");
        ICondition condition = lock.newCondition("condition");

        lock.lock();
        try {
            System.out.println("Lock acquired");
            while (counter.get() != 1) {
                condition.await();
                System.out.println("Waiting ");
            }
        } finally {
            lock.unlock();
        }
        System.out.println("Wait finished");
    }
}