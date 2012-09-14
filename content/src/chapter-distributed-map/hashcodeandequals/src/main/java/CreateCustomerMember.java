import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

public class CreateCustomerMember {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        Map<String,Customer> customerMap = hzInstance.getMap("customers");
        Customer customer = new Customer("Peter");
        customerMap.put(customer.getId(),customer);
        for(Customer c: customerMap.values())
            System.out.println(c);
    }
}
