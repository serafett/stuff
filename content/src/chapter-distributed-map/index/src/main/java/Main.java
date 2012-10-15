import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class Main {
    public static void main(String[] args) {
        HazelcastInstance hzInstance  = Hazelcast.newHazelcastInstance(null);
        IMap personMap = hzInstance.getMap("persons");

        Person person = new Person("foo",true,10);
        person.address.street="foo";

        personMap.put("foo",person);
    }
}
