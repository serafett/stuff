import com.hazelcast.core.*;
import com.hazelcast.executor.RunnableAdapter;
import com.hazelcast.partition.PartitionService;

import java.util.concurrent.*;
public class MasterMember {
    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IExecutorService executorService = hz.getExecutorService("executor");
        for (Member member : hz.getCluster().getMembers()) {
            EchoTask task = new EchoTask("echo" + member.getInetSocketAddress());
            executorService.executeOnMember(task, member);
        }
    }
}
