import com.hazelcast.core.*;
import java.util.concurrent.Executor;
public class MasterMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        IExecutorService executor = hzInstance.getExecutorService("executor");
        for (int k = 1; k <= 1; k++) {
            Thread.sleep(1000);
            System.out.println("Producing echo task: " + k);
            executor.execute(new EchoTask("" + k));
        }
        System.out.println("MasterMember finished!");

        executor.shutdown();
        executor.execute(new EchoTask("foo"));
    }
}
