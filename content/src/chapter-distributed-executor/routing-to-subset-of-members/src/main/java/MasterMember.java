import com.hazelcast.core.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
public class MasterMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        Map map = hzInstance.getMap("map");
        //insert dummy data
        for (int k = 0; k < 15; k++)
            map.put(UUID.randomUUID().toString(), null);
        //fork the tasks.
        Set<Member> members = hzInstance.getCluster().getMembers();
        MultiTask<Integer> task = new MultiTask<Integer>(new SumTask(), members);
        ExecutorService executorService = hzInstance.getExecutorService();
        executorService.execute(task);
        //join the results.
        Collection<Integer> results = task.get();
        int x = 0;
        for (Integer i : results) x += i;
        System.out.println("Result: " + x);
    }
}
