import com.hazelcast.spi.*;
import java.util.Properties;
public class DistributedCounterService implements ManagedService {
    private NodeEngine nodeEngine;
    public void init(NodeEngine nodeEngine, Properties properties) {
        System.out.println("DistributedCounterService.init");
        this.nodeEngine = nodeEngine;
    }
    public void shutdown() {
        System.out.println("DistributedCounterService.shutdown");
    }
}
