import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;

import java.util.concurrent.atomic.AtomicLong;

public class CounterService {
    public static final AtomicLong COLLISION_COUNTER = new AtomicLong();

    private final IMap<String, Counter> counterMap = Hazelcast.getMap("counters");

    public String create(int value) {
        Counter counter = new Counter(value);
        counterMap.put(counter.getId(), counter);
        return counter.getId();
    }

    public long count(String id) {
        return counterMap.get(id).getValue();
    }

    public void increment(String counterId) {
        for (; ; ) {
            Counter oldCounter = counterMap.get(counterId);
            if (oldCounter == null) throw new IllegalArgumentException();
            Counter newCounter = new Counter(counterId, oldCounter.getValue(), oldCounter.getVersion());
            newCounter.inc();
            newCounter.incVersion();
            if (counterMap.replace(counterId, oldCounter, newCounter)) {
                return;
            } else {
                COLLISION_COUNTER.incrementAndGet();
            }
        }
    }
}