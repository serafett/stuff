import com.hazelcast.core.*;
import java.util.concurrent.Executor;
public class MasterMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        Executor executor = hzInstance.getExecutorService("executor");
        for (int k = 1; k <= 1000; k++) {
            Thread.sleep(1000);
            System.out.println("Producing Task: " + k);
            executor.execute(new EchoTask("" + k));
        }
        System.out.println("MasterMember finished!");
    }
}
