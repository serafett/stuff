import com.hazelcast.core.*;
import java.util.*;
public class SubscribedMember {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        ITopic<Date> topic = hazelcastInstance.getTopic("topic");
        topic.addMessageListener(new MessageListenerImpl());
        System.out.println("Subscribed");
    }
    private static class MessageListenerImpl implements MessageListener<Date> {
        @Override
        public void onMessage(Message<Date> m) {
            System.out.println("Received: "+m.getMessageObject());
        }
    }
}
