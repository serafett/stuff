import com.hazelcast.core.*;
import java.util.concurrent.*;
public class MasterMember {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        ExecutorService es = hzInstance.getExecutorService();
        DistributedTask<Long> task = new DistributedTask<Long>(new FibonacciCallable(10));
        ExecutionCallback<Long> executionCallback = new ExecutionCallback<Long>() {
            public void done(Future<Long> future) {
                try {
                    if (!future.isCancelled()) {
                        System.out.println("Result: " + future.get());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        task.setExecutionCallback(executionCallback);
        es.execute(task);
        System.out.println("Fibonacci task submitted");
    }
}
