import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.ExecutorService;

public class MasterNode {
    public static void main(String[] args) throws Exception {
        HazelcastInstance defaultInstance = Hazelcast.getDefaultInstance();
        ExecutorService executor = defaultInstance.getExecutorService();
        executor.execute();
    }
}
