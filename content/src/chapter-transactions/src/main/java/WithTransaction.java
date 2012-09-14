import com.hazelcast.core.*;
import java.util.Map;
public class WithTransaction {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        Map map = hzInstance.getMap("map");
        Transaction txn = hzInstance.getTransaction();
        txn.begin();
        try {
            map.put("1", "1");
            map.put("2", "2");
            if(true)throw new RuntimeException();
            txn.commit();
        } catch (Throwable t) {
            txn.rollback();
        }
        System.out.println("Map.size: " + map.size());
    }
}
