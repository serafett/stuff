import com.hazelblast.server.pojoslice.Exposed;
import com.hazelblast.server.pojoslice.HazelcastInstanceProvider;
import com.hazelcast.core.HazelcastInstance;

public class Pojo implements HazelcastInstanceProvider {

    @Exposed
    public final CustomerService customerService;
    private final HazelcastInstance hzInstance;

    public Pojo(HazelcastInstance hzInstance) {
        this.hzInstance = hzInstance;
        this.customerService = new CustomerServiceImpl(hzInstance);
    }

    @Override
    public HazelcastInstance getHazelcastInstance() {
        return hzInstance;
    }
}
