import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;

public class CounterService {
    private final IMap<String, Counter> counterMap = Hazelcast.getMap("counters");

    public String create(int value) {
        Counter counter = new Counter(value);
        counterMap.put(counter.getId(), counter);
        return counter.getId();
    }

    public long count(String id){
        return counterMap.get(id).getValue();
    }

    public void brokenIncrement(String counterId) {
        Counter counter = counterMap.get(counterId);
        if (counter == null) throw new IllegalArgumentException();
        counter.inc();
    }

    public void increment(String counterId) {
        counterMap.lock(counterId);
        try {
            Counter counter = counterMap.get(counterId);
            if (counter == null) throw new IllegalArgumentException();
            counter.inc();
            counterMap.put(counterId,counter);
        } finally {
            counterMap.unlock(counterId);
        }
    }
}