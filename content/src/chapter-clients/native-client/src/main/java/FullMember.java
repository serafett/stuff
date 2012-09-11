import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
public class FullMember {
    public static void main(String[] args) {
        Config config = new Config();
        Hazelcast.newHazelcastInstance(config);
        System.out.println("Full member started!");
    }
}
