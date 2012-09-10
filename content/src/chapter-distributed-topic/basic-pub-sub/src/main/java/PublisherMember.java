import com.hazelcast.core.*;
import java.util.Date;
public class PublisherMember {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        ITopic<Date> topic = hazelcastInstance.getTopic("topic");
        topic.publish(new Date());
        System.out.println("Published");
    }
}
