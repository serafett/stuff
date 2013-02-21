import com.hazelcast.core.*;

public class NotifyMember {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IAtomicLong counter = hz.getAtomicLong("counter");
        ILock lock = hz.getLock("lock");
        ICondition condition = lock.newCondition();
        lock.lock();
        try {
            counter.set(1);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
        System.out.println("Value changed");
    }
}