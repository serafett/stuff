import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.Executor;

public class WorkProducer {
    private static HazelcastInstance getHazelcastInstance() {
        Config config = new Config();
        config.setLiteMember(true);
        return Hazelcast.newHazelcastInstance(config);
    }

    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = getHazelcastInstance();
        Executor executor = hazelcastInstance.getExecutorService("executor");
        for(int k=0;k<1000;k++){
            Thread.sleep(1000);
            executor.execute(new Work(""+k));
        }
    }
}
