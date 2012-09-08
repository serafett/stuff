import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;

import java.util.Date;

public class PublisherNode {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        ITopic<String> topic = hazelcastInstance.getTopic("topic");
        topic.publish(new Date().toString());
        System.out.println("Finished Publishing");
    }
}
