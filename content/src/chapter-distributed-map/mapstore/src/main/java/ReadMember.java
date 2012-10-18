import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class ReadMember {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        IMap<Long, Person> personMap = hzInstance.getMap("personMap");
        Person p = personMap.get(1L);
        System.out.println(p);
    }
}
