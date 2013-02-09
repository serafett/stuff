import com.hazelcast.core.*;
import com.hazelcast.partition.PartitionService;

import java.util.concurrent.*;
public class MasterMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        ExecutorService executorService = hzInstance.getExecutorService("executor");
        for (Member member : hzInstance.getCluster().getMembers()) {
            EchoTask task = new EchoTask("echo" + member.getInetSocketAddress());
            executorService.execute(new DistributedTask(task, null, member));
        }
    }
}
