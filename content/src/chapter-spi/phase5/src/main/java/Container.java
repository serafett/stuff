import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

class Container {
    private final ConcurrentMap<String, AtomicInteger> counterMap = new ConcurrentHashMap<>();

    public int inc(String id, int amount) {
        AtomicInteger counter = counterMap.get(id);
        if (counter == null) {
            counter = new AtomicInteger();
            AtomicInteger found = counterMap.putIfAbsent(id, counter);
            counter = found == null ? counter : found;
        }
        return counter.addAndGet(amount);
    }

    void clear() {
        counterMap.clear();
    }

    void applyMigrationData(Map<String, Integer> migrationData) {
        for (Map.Entry<String, Integer> entry : migrationData.entrySet()) {
            counterMap.put(entry.getKey(), new AtomicInteger(entry.getValue()));
        }
    }

    Map<String, Integer> toMigrationData() {
        Map<String, Integer> migrationData = new HashMap<>();

        for (Map.Entry<String, AtomicInteger> entry : counterMap.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue().get();
            migrationData.put(name, count);
        }
        return migrationData;
    }
}
