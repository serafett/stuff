import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.Serializable;
import java.util.Map;

public class BrokenKeyMember {

    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        Map<BrokenKey,String> map = hzInstance.getMap("map");
        BrokenKey key1 = new BrokenKey("a","b");
        BrokenKey key2 = new BrokenKey("a","c");

        map.put(key1, "foo");

        System.out.println("key1.equals(key2):"+key1.equals(key2));
        System.out.println("key1.hashcode == key2.hashcode: "+ (key1.hashCode()==key2.hashCode()));

        String result = map.get(key2);
        System.out.println(result);
    }

    private static class BrokenKey implements Serializable {
        private final String significant;
        private final String insignificant;

        public BrokenKey(String significant, String insignificant) {
            this.significant = significant;
            this.insignificant = insignificant;
        }
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BrokenKey brokenKey = (BrokenKey) o;
            if (!significant.equals(brokenKey.significant)) return false;
            return true;
        }
        public int hashCode() {
            return significant.hashCode();
        }
    }
}
