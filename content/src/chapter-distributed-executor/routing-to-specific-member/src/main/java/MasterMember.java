import com.hazelcast.core.*;
import java.util.Set;
import java.util.concurrent.ExecutorService;
public class MasterMember {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        ExecutorService executorService = hzInstance.getExecutorService();
        Set<Member> members = hzInstance.getCluster().getMembers();
        int k=0;
        for(Member member:members){
            EchoTask task = new EchoTask("echo-"+k);
            DistributedTask distributedTask = new DistributedTask(task,member);
            executorService.execute(distributedTask);
            k++;
        }
    }
}
