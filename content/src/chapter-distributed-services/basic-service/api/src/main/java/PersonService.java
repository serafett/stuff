import com.hazelblast.client.annotations.*;
@DistributedService
public interface PersonService {
    @Partitioned
    void create(@PartitionKey String id, String name);
    @Partitioned
    Person get(@PartitionKey String id);
}
