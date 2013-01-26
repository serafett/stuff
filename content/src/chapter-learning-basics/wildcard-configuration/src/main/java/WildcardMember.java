import com.hazelcast.core.*;
import java.util.Map;
public class WildcardMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        Map map1 = hzInstance.getMap("testmap1");
        Map map2 = hzInstance.getMap("testmap2");

        map1.put("foo","foo");
        for(;;){
            System.out.println("size:"+map1.size());
            Thread.sleep(1000);
        }
    }
}
