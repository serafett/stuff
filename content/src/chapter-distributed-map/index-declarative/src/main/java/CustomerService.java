import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;

import java.util.Map;
import java.util.Set;

public class CustomerService{
	private final IMap<String,Customer> customerMap = Hazelcast.getMap("customers");

	public String create(String name){
		Customer customer = new Customer(name);
	    customerMap.put(customer.getId(), customer);
        System.out.printf("Customer %s with id %s and name %s created\n",name,customer.getId(),name);
	    return customer.getId();
    }

    public Set<Customer> getWithName(String name) {
        EntryObject e = new PredicateBuilder().getEntryObject();
        Predicate predicate = e.get("name").equal(name);
        return (Set<Customer>) customerMap.values(predicate);
    }
}