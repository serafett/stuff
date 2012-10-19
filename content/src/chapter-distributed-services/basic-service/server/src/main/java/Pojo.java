import com.hazelblast.server.pojoslice.Exposed;
import com.hazelblast.server.pojoslice.HazelcastInstanceProvider;
import com.hazelcast.core.HazelcastInstance;

public class Pojo implements HazelcastInstanceProvider {

    @Exposed
    public final EmployeeService employeeService;
    private final HazelcastInstance hzInstance;

    public Pojo(HazelcastInstance hzInstance) {
        this.hzInstance = hzInstance;
        this.employeeService = new EmployeeServiceImpl(hzInstance);
    }

    @Override
    public HazelcastInstance getHazelcastInstance() {
        return hzInstance;
    }
}
