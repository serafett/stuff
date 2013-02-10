import com.hazelcast.core.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MasterMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        Map<String,Integer> map = hzInstance.getMap("map");
        for (int k = 0; k < 5; k++)
            map.put(UUID.randomUUID().toString(), 1);
       IExecutorService executor = hzInstance.getExecutorService("executor");
        Map<Member,Future<Integer>> result = executor.submitToAllMembers (new SumTask());
        int sum = 0;
        for(Future<Integer> future: result.values())
            sum+=future.get();

        System.out.println("Result: " + sum);
    }
}
