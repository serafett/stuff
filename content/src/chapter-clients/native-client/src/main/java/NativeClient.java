import com.hazelcast.client.*;
import com.hazelcast.core.HazelcastInstance;
import java.util.concurrent.BlockingQueue;
public class NativeClient {
    public static void main(String[] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("127.0.0.1");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        BlockingQueue<String> queue = client.getQueue("queue");
        queue.put("Hello!");
        System.out.println("Message send by lite member!");
    }
}
