import com.hazelcast.core.Hazelcast;
import java.util.Map;
public class CustomerServiceImpl implements CustomerService {

    private final Map<String, Customer> customerMap = Hazelcast.getMap("customers");

    public String create(String name) {
        Customer c = new Customer(name);
        customerMap.put(c.getId(), c);
        System.out.printf("Customer %s with id %s and name %s created\n",
                name, c.getId(), name);
        return c.getId();
    }

    public Customer get(String id) {
        return customerMap.get(id);
    }
}
