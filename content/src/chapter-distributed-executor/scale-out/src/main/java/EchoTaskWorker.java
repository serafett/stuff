import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.Executor;

public class EchoTaskWorker {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
//        Executor executor = hazelcastInstance.getExecutorService("executor");

    }
}
