import com.hazelcast.spi.ManagedService;
import com.hazelcast.spi.NodeEngine;

import java.util.Properties;

public class DistributedCounterService implements ManagedService {
    private NodeEngine nodeEngine;

    @Override
    public void init(NodeEngine nodeEngine, Properties properties) {
        System.out.println("DistributedCounterService.init");
        this.nodeEngine = nodeEngine;
    }

    @Override
    public void shutdown() {
        System.out.println("DistributedCounterService.shutdown");
    }
}
