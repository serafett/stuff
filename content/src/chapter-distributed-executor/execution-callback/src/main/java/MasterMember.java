import com.hazelcast.core.*;
public class MasterMember {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        IExecutorService executor = hzInstance.getExecutorService("executor");

        final FibonacciCallable fibonacciCallable = new FibonacciCallable(10);
        ExecutionCallback<Long> executionCallback = new ExecutionCallback<Long>() {
            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onResponse(Long response) {
                System.out.println("Result: " + response);
            }
        };
        executor.submit(fibonacciCallable, executionCallback);
        System.out.println("Fibonacci task submitted");
    }
}
