import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Transaction;

import java.util.Map;

public class TransactionalMember {
    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        Map<String, String> map = hz.getMap("map");
        Transaction txn = hz.getTransaction();
        txn.begin();
        try {
            map.put("1", "1");
            map.put("2", "2");
            txn.commit();
        } catch (Throwable t) {
            txn.rollback();
        }
        System.out.println("Map.size: " + map.size());
    }
}
