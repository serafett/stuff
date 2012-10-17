import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

public class Main {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        Map<Long,Person> personMap = hzInstance.getMap("personMap");

        long key = System.nanoTime();
        Person p = new Person("John"+key);
        personMap.put(key,p);

        for(Map.Entry<Long,Person> entry: personMap.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        System.exit(0);
    }
}
