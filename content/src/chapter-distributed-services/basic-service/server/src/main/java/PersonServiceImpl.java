import com.hazelcast.core.HazelcastInstance;
import java.util.Map;
public class PersonServiceImpl implements PersonService {
    private final Map<String, Person> personMap;
    public PersonServiceImpl(HazelcastInstance hzInstance) {
        this.personMap =hzInstance.getMap("persons");
    }
    public void create(String id, String name) {
        Person e = new Person(id, name);
        personMap.put(e.id, e);
    }
    public Person get(String id) {
        return personMap.get(id);
    }
}
