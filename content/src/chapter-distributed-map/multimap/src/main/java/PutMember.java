import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;
public class PutMember {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        MultiMap<String,String> map = hzInstance.getMultiMap("map");

        map.put("Peter","England");
        map.put("Peter","Holland");
        map.put("Talip","Turkey");
     }
}
