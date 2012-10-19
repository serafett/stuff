import com.hazelcast.core.HazelcastInstance;
import java.util.Map;
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> customerMap;
    public EmployeeServiceImpl(HazelcastInstance hzInstance) {
        this.customerMap =hzInstance.getMap("customers");
    }
    public void create(String id, String name) {
        Employee e = new Employee(id, name);
        customerMap.put(e.id, e);
        System.out.printf("Created: "+e);
    }
    public Employee get(String id) {
        return customerMap.get(id);
    }
}
