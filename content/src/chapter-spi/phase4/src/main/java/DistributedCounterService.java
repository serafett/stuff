import com.hazelcast.core.DistributedObject;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.spi.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DistributedCounterService implements ManagedService, RemoteService, MigrationAwareService {
    private NodeEngine nodeEngine;
    Container[] containers;

    public void init(NodeEngine nodeEngine, Properties properties) {
        this.nodeEngine = nodeEngine;
        containers = new Container[nodeEngine.getPartitionService().getPartitionCount()];
        for (int k = 0; k < containers.length; k++)
            containers[k] = new Container();
    }

    public void shutdown() {
    }

    public DistributedObject createDistributedObject(Object objectId) {
        return new DistributedCounterProxy(String.valueOf(objectId), nodeEngine);
    }

    public String getServiceName() {
        return "DistributedCounterService";
    }

    public class Container {
        private ConcurrentMap<String, AtomicInteger> counterMap = new ConcurrentHashMap<>();

        public int inc(String id, int amount) {
            AtomicInteger counter = counterMap.get(id);
            if (counter == null) {
                counter = new AtomicInteger();
                AtomicInteger found = counterMap.putIfAbsent(id, counter);
                counter = found == null ? counter : found;
            }
            return counter.addAndGet(amount);
        }
    }

    @Override
    public void beforeMigration(MigrationServiceEvent migrationServiceEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Operation prepareMigrationOperation(MigrationServiceEvent migrationServiceEvent) {
        Map<String, Integer> migrationData = new HashMap<>();
        Container container = containers[migrationServiceEvent.getPartitionId()];

        for (Map.Entry<String, AtomicInteger> entry : container.counterMap.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue().get();
            migrationData.put(name, count);
        }
        if (migrationData.isEmpty()) {
            return null;
        }
        return new MigrationOperation(migrationData);
    }

    public static class MigrationOperation extends AbstractOperation {

        Map<String, Integer> migrationData;

        public MigrationOperation() {
        }

        public MigrationOperation(Map<String, Integer> migrationData) {
            this.migrationData = migrationData;
        }

        protected void writeInternal(ObjectDataOutput out) throws IOException {
            out.writeInt(migrationData.size());
            for (Map.Entry<String, Integer> entry : migrationData.entrySet()) {
                out.writeUTF(entry.getKey());
                out.writeInt(entry.getValue());
            }
        }

        protected void readInternal(ObjectDataInput in) throws IOException {
            int size = in.readInt();
            migrationData = new HashMap<>();
            for (int i = 0; i < size; i++)
                migrationData.put(in.readUTF(), in.readInt());
        }

        public void run() throws Exception {
            DistributedCounterService service = getService();
            Container container = service.containers[getPartitionId()];
            for (Map.Entry<String, Integer> entry : migrationData.entrySet()) {
                container.counterMap.put(entry.getKey(), new AtomicInteger(entry.getValue()));
            }
        }
    }

    //is this method called on both the primary and backup?
    @Override
    public void commitMigration(MigrationServiceEvent migrationServiceEvent) {
        //migrationServiceEvent.getMigrationType().
        //To change body of implemented methods use File | Settings | File Templates.
    }

    //is this method called on both the primary and backup?
    @Override
    public void rollbackMigration(MigrationServiceEvent migrationServiceEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getMaxBackupCount() {
        return 1;
    }

    public DistributedObject createDistributedObjectForClient(Object objectId) {
        return null;
    }

    public void destroyDistributedObject(Object objectId) {
    }
}
