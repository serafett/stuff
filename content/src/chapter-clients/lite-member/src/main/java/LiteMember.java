import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.Executor;

public class LiteMember {
    public static void main(String[] args){
        Config config = new Config();
        config.setLiteMember(true);

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

    }
}
