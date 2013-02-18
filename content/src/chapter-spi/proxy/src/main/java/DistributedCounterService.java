import com.hazelcast.core.DistributedObject;
import com.hazelcast.spi.ManagedService;
import com.hazelcast.spi.NodeEngine;
import com.hazelcast.spi.RemoteService;

import java.util.Properties;

public class DistributedCounterService implements ManagedService, RemoteService {
    private NodeEngine nodeEngine;

    @Override
    public void init(NodeEngine nodeEngine, Properties properties) {
        this.nodeEngine = nodeEngine;
    }

    @Override
    public void shutdown() {
    }

    @Override
    public DistributedObject createDistributedObject(Object objectId) {
        return new CounterProxy(String.valueOf(objectId), nodeEngine);
    }

    @Override
    public String getServiceName() {
        return "DistributedCounterProxy";
    }

    @Override
    public DistributedObject createDistributedObjectForClient(Object objectId) {
        return null;
    }

    @Override
    public void destroyDistributedObject(Object objectId) {
    }
}
