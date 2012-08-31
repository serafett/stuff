import com.hazelcast.core.Hazelcast;
import com.hazelcast.partition.Partition;
import com.hazelcast.partition.PartitionService;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();

        String customerId = customerService.create("Homer");
        String orderId = orderService.placeOrder(customerId, "123", 1);

        PartitionService partitionService = Hazelcast.getPartitionService();
        Partition customerPartition = partitionService.getPartition(customerId);
        Partition orderPartition = partitionService.getPartition(new OrderKey(orderId, customerId));
        System.out.printf("CustomerPartition: %s\n", customerPartition.getPartitionId());
        System.out.printf("OrderPartition: %s\n", orderPartition.getPartitionId());
    }
}
