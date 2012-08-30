import com.hazelcast.core.Hazelcast;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ListenerMain {

    public static void main(String[] args) throws Exception {

        Lock lock = Hazelcast.getLock("lock");
        Condition condition = lock.newCondition();
        lock.lock();
        try {
            condition.await();
        } finally {
            lock.unlock();
        }

        System.out.println("Listener completed");
    }
}
