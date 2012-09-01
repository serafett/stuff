import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class SlaveNode {

    public static void main(String[] args) throws Exception {
        Hazelcast.getDefaultInstance();
    }
}
