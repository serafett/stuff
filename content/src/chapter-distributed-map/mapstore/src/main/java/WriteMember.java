import com.hazelcast.core.*;
public class WriteMember {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        IMap<Long, Person> personMap = hzInstance.getMap("personMap");
        personMap.put(1L, new Person("Peter"));
        hzInstance.getLifecycleService().shutdown();
        System.exit(0);

        Runtime.getRuntime().addShutdownHook();
    }
}
