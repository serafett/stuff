import com.hazelcast.core.*;
import com.hazelcast.spi.*;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DistributedCounterService implements ManagedService, RemoteService {
    private NodeEngine nodeEngine;
    Container[] containers;
    public void init(NodeEngine nodeEngine, Properties properties) {
        this.nodeEngine = nodeEngine;
        containers = new Container[nodeEngine.getPartitionService().getPartitionCount()];
        for(int k=0;k<containers.length;k++)
            containers[k]=new Container();
    }
    public void shutdown() {
    }
    public DistributedObject createDistributedObject(Object objectId) {
        return new DistributedCounterProxy(String.valueOf(objectId),nodeEngine);
    }
    public String getServiceName() {
        return "DistributedCounterService";
    }
    public class Container {
        private final ConcurrentMap<String,AtomicInteger> counterMap = new ConcurrentHashMap<>();
        public int inc(String id,  int amount) {
            AtomicInteger counter = counterMap.get(id);
            if(counter == null){
                counter = new AtomicInteger();
                AtomicInteger found = counterMap.putIfAbsent(id, counter);
                counter = found == null?counter : found;
            }
            return counter.addAndGet(amount);
        }
    }
    public DistributedObject createDistributedObjectForClient(Object objectId) {
        return null;
    }
    public void destroyDistributedObject(Object objectId) {}
}
