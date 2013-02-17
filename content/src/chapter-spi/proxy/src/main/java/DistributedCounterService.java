import com.hazelcast.core.DistributedObject;
import com.hazelcast.spi.ManagedService;
import com.hazelcast.spi.NodeEngine;
import com.hazelcast.spi.RemoteService;

import java.util.Properties;

public class DistributedCounterService implements ManagedService, RemoteService {
    private NodeEngine nodeEngine;

    public void init(NodeEngine nodeEngine, Properties properties) {
        this.nodeEngine = nodeEngine;
    }

    public void shutdown() {
    }

    public DistributedObject createDistributedObject(Object objectId) {
        return new DistributedCounterProxy(String.valueOf(objectId), nodeEngine);
    }

    public String getServiceName() {
        return "DistributedCounterProxy";
    }

    public DistributedObject createDistributedObjectForClient(Object objectId) {
        return null;
    }

    public void destroyDistributedObject(Object objectId) {
    }
}
