import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;

import java.io.Serializable;
import java.util.Collection;

public class PrintMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        MultiMap<String, String> map = hzInstance.getMultiMap("map");
        for (String key : map.keySet()) {
            Collection<String> values = map.get(key);
            System.out.printf("%s -> %s\n", key, values);
        }

        map.remove("Peter", "Holland");
        map.remove("Peter", "England");


        //System.out.println(c);
        System.out.println(map.get("Peter"));
        MultiMap m = hzInstance.getMultiMap("foo");
        for(int k=0;k<10;k++){
            m.put("a",new Foo());
        }

        System.out.println("removing");
        m.remove("a", new Foo());
    }

    static class Foo implements Serializable {
        public int hashCode() {
            System.out.println("HashCode called");
            return super.hashCode();
        }

        public boolean equals(Object that) {
            System.out.println("Equals called");
            return super.equals(that);
        }
    }
}
