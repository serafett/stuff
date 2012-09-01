import com.hazelcast.core.*;
import java.util.*;
import java.util.concurrent.ExecutorService;

public class MasterNode {

    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        insertDummyData(hazelcastInstance);

        int x = count(hazelcastInstance);
        System.out.println("Result: " + x);
    }

    private static int count(HazelcastInstance hazelcastInstance) throws Exception {
        Set<Member> members = hazelcastInstance.getCluster().getMembers();
        MultiTask<Integer> task = new MultiTask<Integer>(new CountTask(), members);
        ExecutorService executorService = hazelcastInstance.getExecutorService();
        executorService.execute(task);

        Collection<Integer> results = task.get();
        int x = 0;
        for (Integer i : results) x += i;
        return x;
    }

    private static void insertDummyData(HazelcastInstance hazelcastInstance) {
        Map map = hazelcastInstance.getMap("map");

        for (int k = 0; k < 15; k++) {
            map.put(UUID.randomUUID().toString(), 1);
        }
    }
}
