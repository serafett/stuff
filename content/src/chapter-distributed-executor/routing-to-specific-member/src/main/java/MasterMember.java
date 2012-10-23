import com.hazelcast.core.*;
import java.util.concurrent.*;
public class MasterMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        ExecutorService executorService = hzInstance.getExecutorService();
        for (Member member : hzInstance.getCluster().getMembers()) {
            EchoTask task = new EchoTask("echo" + member.getInetSocketAddress());
            Callable callable = new DistributedTask.DistributedRunnableAdapterImpl(task, null);
            executorService.execute(new DistributedTask(callable, member));
        }
    }
}
