import com.hazelcast.core.*;
import com.hazelcast.executor.RunnableAdapter;
import com.hazelcast.partition.PartitionService;

import java.util.concurrent.*;
public class MasterMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        IExecutorService executorService = hzInstance.getExecutorService("executor");
        for (Member member : hzInstance.getCluster().getMembers()) {
            EchoTask task = new EchoTask("echo" + member.getInetSocketAddress());
            executorService.executeOnMember(task, member);
        }
    }
}
