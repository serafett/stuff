import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.SqlPredicate;

import java.util.HashSet;
import java.util.Set;

public class CustomerService {
    private final IMap<String, Customer> customerMap = Hazelcast.getMap("customers");

    public String create(String name) {
        Customer customer = new Customer(name);
        customerMap.put(customer.getId(), customer);
        System.out.printf("Customer %s with id %s and name %s created\n", name, customer.getId(), name);
        return customer.getId();
    }

    public Set<Customer> getWithNameNaive(String name) {
        Set<Customer> result = new HashSet<Customer>();
        for (Customer customer : customerMap.values()) {
            if (customer.getName().equals(name))
                result.add(customer);
        }
        return result;
    }

    public Set<Customer> getWithName(String name) {
        EntryObject e = new PredicateBuilder().getEntryObject();
        Predicate predicate = e.get("name").equal(name);
        return (Set<Customer>) customerMap.values(predicate);
    }

    public Set<Customer> getWithNameSql(String name) {
        Predicate predicate = new SqlPredicate(String.format("name = '%s'", name));
        return (Set<Customer>) customerMap.values(predicate);
    }

    public Customer get(String id) {
        return customerMap.get(id);
    }
}