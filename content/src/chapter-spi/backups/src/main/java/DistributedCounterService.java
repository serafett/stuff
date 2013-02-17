import com.hazelcast.core.DistributedObject;
import com.hazelcast.partition.MigrationEndpoint;
import com.hazelcast.partition.MigrationType;
import com.hazelcast.spi.*;

import java.util.Map;
import java.util.Properties;

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

    @Override
    public void beforeMigration(MigrationServiceEvent e) {
        //no-op
    }

    @Override
    public Operation prepareMigrationOperation(MigrationServiceEvent e) {
        if (e.getReplicaIndex() > 1) return null;

        Container container = containers[e.getPartitionId()];
        Map<String, Integer> migrationData = container.toMigrationData();
        if (migrationData.isEmpty()) return null;
        return new MigrationOperation(migrationData);
    }

    @Override
    public void commitMigration(MigrationServiceEvent e) {
        if (e.getMigrationEndpoint() == MigrationEndpoint.SOURCE
                && e.getMigrationType() == MigrationType.MOVE) {
            containers[e.getPartitionId()].clear();
        }
    }

    @Override
    public void rollbackMigration(MigrationServiceEvent e) {
        if (e.getMigrationEndpoint() == MigrationEndpoint.DESTINATION)
            containers[e.getPartitionId()].clear();
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
