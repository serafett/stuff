import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

class Container {
    private final ConcurrentMap<String,AtomicInteger> counterMap = new ConcurrentHashMap<>();

    int inc(String id,  int amount) {
        AtomicInteger counter = counterMap.get(id);
        if(counter == null){
            counter = new AtomicInteger();
            AtomicInteger found = counterMap.putIfAbsent(id, counter);
            counter = found == null?counter : found;
        }
        return counter.addAndGet(amount);
    }
}
