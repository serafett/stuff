import com.hazelcast.core.*;
public class ReadMember {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        IMap<Long, Person> personMap = hzInstance.getMap("personMap");
        Person p = personMap.get(1L);
        System.out.println(p);
    }
}
