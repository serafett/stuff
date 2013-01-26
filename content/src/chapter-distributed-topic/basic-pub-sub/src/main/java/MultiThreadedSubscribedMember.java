import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MultiThreadedSubscribedMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        ITopic<Date> topic = hzInstance.getTopic("topic");
        topic.addMessageListener(new MessageListenerImpl());
        System.out.println("Subscribed");
    }
    private final static Executor executor = Executors.newFixedThreadPool(10);
    private static class MessageListenerImpl implements MessageListener<Date> {
        public void onMessage(final Message<Date> m) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Received: " + m.getMessageObject());
                }
            };
            executor.execute(task);
        }
    }
}
