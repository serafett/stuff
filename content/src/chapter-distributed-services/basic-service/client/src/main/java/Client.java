import com.hazelblast.client.ProxyProvider;
import com.hazelblast.client.impl.ProxyProviderImpl;
import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import java.util.UUID;
public class Client {
    public static void main(String[] args) {
        Config config = new Config();
        config.setLiteMember(true);
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(config);
        ProxyProvider proxyProvider = new ProxyProviderImpl(hzInstance);
        EmployeeService employeeService = proxyProvider.getProxy(EmployeeService.class);
        String id = UUID.randomUUID().toString();
        employeeService.create(id,"foo");
        Employee e = employeeService.get(id);
        System.out.println("Created: "+e);
        System.exit(0);
    }
}
