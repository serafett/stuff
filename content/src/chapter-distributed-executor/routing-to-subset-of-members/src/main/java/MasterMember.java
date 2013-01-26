import com.hazelcast.core.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
public class MasterMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        Map<String,Integer> map = hzInstance.getMap("map");
        for (int k = 0; k < 5; k++)
            map.put(UUID.randomUUID().toString(), 1);
        Set<Member> members = hzInstance.getCluster().getMembers();
        MultiTask<Integer> task = new MultiTask<Integer>(new SumTask(), members);
        hzInstance.getExecutorService().execute(task);
        Collection<Integer> results = task.get();
        int x = 0; for (Integer i : results) x += i;
        System.out.println("Result: " + x);
    }
}
