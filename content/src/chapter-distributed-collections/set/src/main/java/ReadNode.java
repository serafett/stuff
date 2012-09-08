import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.Set;

public class ReadNode {
    public static void main(String[] args) {
         HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();

         Set<String> set = hazelcastInstance.getSet("set");
        Map map = hazelcastInstance.getMap("m:s:set");
        set.add("foo");

        for(String s: set){
             System.out.println(s);
         }
        System.out.println(map.size());
         System.out.println("Reading finished");
     }
}
