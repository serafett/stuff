import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
public class Main {
    public static void main(String[] args){
        XmlConfigBuilder builder = new XmlConfigBuilder();
        Hazelcast.newHazelcastInstance(builder.build());
    }
}
