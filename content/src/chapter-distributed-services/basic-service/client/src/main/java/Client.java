import com.hazelblast.client.ProxyProvider;
import com.hazelblast.client.impl.ProxyProviderImpl;
import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import java.util.UUID;
public class Client {
    public static void main(String[] args) {
        Config config = new Config().setLiteMember(true);
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(config);
        ProxyProvider proxyProvider = new ProxyProviderImpl(hzInstance);
        PersonService personService = proxyProvider.getProxy(PersonService.class);
        String id = UUID.randomUUID().toString();
        personService.create(id, "foo");
        Person p = personService.get(id);
        System.out.println("Created: "+p);
        System.exit(0);
    }
}
