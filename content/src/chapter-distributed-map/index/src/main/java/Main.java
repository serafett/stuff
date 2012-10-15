import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;

import java.util.Random;

public class Main {
    public final static int MAP_SIZE = 100000;
    public final static int SEARCH_COUNT = 1000;

    private static final String[] names = new String[]{"Jacob", "Sophia", "Mason", "Isabella",
            "William", "Emma","Jayden", "Olivia", "Noah", "Ava", "Michael", "Emily",
            "Ethan", "Abigail",  "Alexander", "Madison","Aiden", "Mia","Daniel", "Chloe"};

    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        IMap<String, Person> personMap = hzInstance.getMap("pesrsons");

        System.out.println("Generating testdata");
        Random random = new Random();
        for (int k = 0; k < MAP_SIZE; k++) {
            String name = names[random.nextInt(names.length)];
            personMap.put(""+k, new Person(name));
        }
        System.out.println("Generating testdata completed");

        System.out.println("Starting benchmark");
        long startMs = System.currentTimeMillis();
        for(int k=0;k< SEARCH_COUNT;k++){
           Predicate predicate = Predicates.equal(Predicates.get("name"),names[random.nextInt(names.length)]);
           personMap.values(predicate);
        }
        long durationMs = System.currentTimeMillis()-startMs;
        double performance = (SEARCH_COUNT*1000d)/durationMs;
        System.out.println("Total duration: "+durationMs+" ms");
        System.out.println("Performance: "+performance+" searches per second");
    }
}
