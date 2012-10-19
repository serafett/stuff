import com.hazelblast.client.ProxyProvider;
import com.hazelblast.client.impl.ProxyProviderImpl;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class Client {
    public static void main(String[] args) {
        Config config = new Config();
        config.setLiteMember(true);
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(config);
        ProxyProvider proxyProvider = new ProxyProviderImpl(hzInstance);
        CustomerService customerService = proxyProvider.getProxy(CustomerService.class);
        customerService.create("foo");

        System.out.println("Created");
    }
}
