import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MasterNode {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        ExecutorService executorService = hazelcastInstance.getExecutorService();
        int n = Integer.parseInt(args[0]);
        Future<Long> future = executorService.submit(new FibonacciCallable(n));
        try {
            long result = future.get(10, TimeUnit.SECONDS);
            System.out.println("result: "+result);
        } catch (TimeoutException ex) {
            System.out.println("A timeout happened, the future is cancelled");
            future.cancel(true);
        }
        System.out.println("Finished");
    }
}