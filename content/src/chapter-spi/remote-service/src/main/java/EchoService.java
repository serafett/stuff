import com.hazelcast.core.*;
import com.hazelcast.spi.*;
import java.util.Properties;
public class EchoService implements ManagedService, RemoteService {
    private NodeEngine nodeEngine;
    public void init(NodeEngine nodeEngine, Properties properties) {
        this.nodeEngine = nodeEngine;
    }
    public void shutdown() {
    }
    public DistributedObject createDistributedObject(Object objectId) {
        return new EchoerProxy(String.valueOf(objectId),nodeEngine);
    }
    public String getServiceName() {
        return "EchoService";
    }

    @Override
    public DistributedObject createDistributedObjectForClient(Object objectId) {
        return null;
    }
    @Override
    public void destroyDistributedObject(Object objectId) {}
}
