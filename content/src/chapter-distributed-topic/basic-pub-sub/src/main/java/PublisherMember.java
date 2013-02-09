import com.hazelcast.core.*;
import java.util.Date;
public class PublisherMember {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        ITopic<Date> topic = hzInstance.getTopic("topic");
        topic.publish(new Date());
        System.out.println("Published");
        System.exit(0);
    }
}
