import com.hazelcast.core.Hazelcast;

import java.util.Map;

public class CustomerService {
    private final Map<String, Customer> customerMap = Hazelcast.getMap("customers");

    public String create(String name) {
        Customer customer = new Customer(name);
        customerMap.put(customer.getId(), customer);
        System.out.printf("Customer %s with id %s and name %s created\n",name,customer.getId(),name);
        return customer.getId();
    }

    public void updateName(String customerId, String newName) {
        Customer customer = customerMap.get(customerId);
        if (customer == null) throw new IllegalArgumentException();
        customer.setName(newName);
        customerMap.put(customerId, customer);
        System.out.printf("Updated customer %s to new name %s\n",  customer.getId(), newName);
    }

    public Customer get(String id) {
        return customerMap.get(id);
    }
}