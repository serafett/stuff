import com.hazelcast.core.*;
import com.hazelcast.spi.*;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DistributedCounterService implements ManagedService, RemoteService {
    private NodeEngine nodeEngine;
    DistributedMapContainer[] containers;
    public void init(NodeEngine nodeEngine, Properties properties) {
        this.nodeEngine = nodeEngine;
        containers = new  DistributedMapContainer[nodeEngine.getPartitionService().getPartitionCount()];
        for(int k=0;k<containers.length;k++)
            containers[k]=new DistributedMapContainer();
    }
    public void shutdown() {
    }
    public DistributedObject createDistributedObject(Object objectId) {
        return new DistributedCounterProxy(String.valueOf(objectId),nodeEngine);
    }
    public String getServiceName() {
        return "DistributedCounterService";
    }
    public class DistributedMapContainer{
        private ConcurrentMap<String,AtomicInteger> maps = new ConcurrentHashMap<>();
        public int inc(String id,  int amount) {
            AtomicInteger integer = maps.get(id);
            if(integer == null){
                integer = new AtomicInteger();
                AtomicInteger found = maps.putIfAbsent(id, integer);
                integer = found == null?integer : found;
            }
            return integer.addAndGet(amount);
        }
    }
    public DistributedObject createDistributedObjectForClient(Object objectId) {
        return null;
    }
    public void destroyDistributedObject(Object objectId) {}
}
