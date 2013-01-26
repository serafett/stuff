import com.hazelcast.core.*;
import com.hazelcast.partition.*;
import java.util.Map;
public class DataLocalityMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        Map<Long, Customer> customerMap = hzInstance.getMap("customers");
        Map<OrderKey, Order> orderMap = hzInstance.getMap("orders");

        long customerId = 100;
        long orderId = 200;
        long articleId = 300;

        Customer customer = new Customer(customerId);
        customerMap.put(customer.id, customer);

        OrderKey orderKey = new OrderKey(orderId,customer.id);
        Order order = new Order(orderKey.orderId,customer.id,articleId);
        orderMap.put(orderKey, order);

        PartitionService pService = hzInstance.getPartitionService();
        Partition cPartition = pService.getPartition(customerId);
        Partition kPartition = pService.getPartition(new OrderKey(orderId, customerId));
        Partition oPartition = pService.getPartition(orderId);

        System.out.printf("Partition for customer: %s\n", cPartition.getPartitionId());
        System.out.printf("Partition for order with OrderKey: %s\n", kPartition.getPartitionId());
        System.out.printf("Partition for order without OrderKey: %s\n", oPartition.getPartitionId());
    }
}
