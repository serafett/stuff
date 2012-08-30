import com.hazelcast.core.Hazelcast;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class NotifyMain {

    public static void main(String[] args) throws Exception {

        Lock lock = Hazelcast.getLock("lock");
        Condition condition = lock.newCondition();

        Thread.sleep(60 * 1000);

        lock.lock();
        try {
            condition.notify();
        } finally {
            lock.unlock();
        }
    }
}
