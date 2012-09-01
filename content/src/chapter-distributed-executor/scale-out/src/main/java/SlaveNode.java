import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.Executor;

public class SlaveNode {
    public static void main(String[] args) {
        Hazelcast.getDefaultInstance();
    }
}
