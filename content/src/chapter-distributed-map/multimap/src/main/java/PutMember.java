import com.hazelcast.core.*;
public class PutMember {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        MultiMap<String,String> map = hzInstance.getMultiMap("map");

        map.put("Peter","England");
        map.put("Peter","Holland");
        map.put("Talip","Turkey");
    }
}
