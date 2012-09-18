import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;

import java.util.Set;

public class PredicateMember {

    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        IMap<String, Customer> map = hzInstance.getMap("map");

        map.put("1", new Customer("peter", true, 36));
        map.put("2", new Customer("john", false, 40));
        map.put("3", new Customer("roger", true, 20));

        EntryObject e = new PredicateBuilder().getEntryObject();
        Predicate predicate = e.is("active").and(e.get("age").lessThan(30));

        Set<Customer> employees = (Set<Customer>) map.values(predicate);
        System.out.println("Employees:"+employees);
    }
}
