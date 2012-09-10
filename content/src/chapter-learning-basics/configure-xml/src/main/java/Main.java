import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class Main {
    public static void main(String[] args){
        XmlConfigBuilder builder = new XmlConfigBuilder();
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(builder.build());
    }
}
