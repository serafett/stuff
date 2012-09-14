import com.hazelcast.core.*;
import java.util.*;
public class SubscribedMember {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        ITopic<Date> topic = hzInstance.getTopic("topic");
        topic.addMessageListener(new MessageListenerImpl());
        System.out.println("Subscribed");
    }
    private static class MessageListenerImpl implements MessageListener<Date> {
        public void onMessage(Message<Date> m) {
            System.out.println("Received: "+m.getMessageObject());
        }
    }
}
