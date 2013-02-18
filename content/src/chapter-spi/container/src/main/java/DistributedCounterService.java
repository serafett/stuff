import com.hazelcast.core.DistributedObject;
import com.hazelcast.spi.ManagedService;
import com.hazelcast.spi.NodeEngine;
import com.hazelcast.spi.RemoteService;

import java.util.Properties;

public class DistributedCounterService implements ManagedService, RemoteService {
    private NodeEngine nodeEngine;
    Container[] containers;

    @Override
    public void init(NodeEngine nodeEngine, Properties properties) {
        this.nodeEngine = nodeEngine;
        containers = new Container[nodeEngine.getPartitionService().getPartitionCount()];
        for (int k = 0; k < containers.length; k++)
            containers[k] = new Container();
    }

    @Override
    public void shutdown() {
    }

    @Override
    public DistributedObject createDistributedObject(Object objectId) {
        String id = String.valueOf(objectId);
        return new DistributedCounterProxy(id, nodeEngine);
    }

    @Override
    public String getServiceName() {
        return "DistributedCounterService";
    }

    @Override
    public DistributedObject createDistributedObjectForClient(Object objectId) {
        return null;
    }

    @Override
    public void destroyDistributedObject(Object objectId) {
    }
}
