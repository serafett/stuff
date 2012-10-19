import com.hazelblast.client.annotations.DistributedService;
import com.hazelblast.client.annotations.LoadBalanced;
import com.hazelblast.client.annotations.PartitionKey;
import com.hazelblast.client.annotations.Partitioned;

@DistributedService
public interface CustomerService {

    @LoadBalanced
    String create(@PartitionKey String name);

    @Partitioned
    Customer get(@PartitionKey String id);
}
