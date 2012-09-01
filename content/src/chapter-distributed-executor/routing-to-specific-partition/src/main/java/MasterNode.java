import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.concurrent.ExecutorService;

public class MasterNode {

    public static void main(String[] args) throws Exception {
        HazelcastInstance defaultInstance = Hazelcast.getDefaultInstance();
        Map map = defaultInstance.getMap("data");
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");

        ExecutorService executor = defaultInstance.getExecutorService();


        executor.execute(new PartitionedTask("1"));
        executor.execute(new PartitionedTask("1"));
        executor.execute(new PartitionedTask("1"));
    }
}
