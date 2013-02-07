import com.hazelcast.spi.*;
import java.util.Properties;
public class DistributedCounterService implements ManagedService {
    public void init(NodeEngine nodeEngine, Properties properties) {
        System.out.println("DistributedCounterService.init");
    }
    public void shutdown() {
        System.out.println("DistributedCounterService.shutdown");
    }
}
