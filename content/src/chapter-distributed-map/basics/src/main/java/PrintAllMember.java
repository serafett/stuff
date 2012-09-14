import com.hazelcast.core.*;
import java.util.Map;
public class PrintAllMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        Map<String, String> map = hzInstance.getMap("map");
        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }
}
