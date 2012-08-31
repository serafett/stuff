import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.Executor;

public class WorkConsumer {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(new Config());
    }
}
