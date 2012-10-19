import com.hazelblast.client.annotations.*;
@DistributedService
public interface EmployeeService {
    @Partitioned
    void create(@PartitionKey String id, String name);
    @Partitioned
    Employee get(@PartitionKey String id);
}
