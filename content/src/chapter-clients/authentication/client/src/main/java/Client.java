import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.nio.SocketInterceptor;
import com.hazelcast.security.UsernamePasswordCredentials;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class Client {
    public static void main(String[] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig().addAddress("127.0.0.1");
        clientConfig.setCredentials(new UsernamePasswordCredentials("dev","dev-pass"));
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        BlockingQueue<String> queue = client.getQueue("queue");
        queue.put("Hello!");
        System.out.println("Message send by client!");
        System.exit(0);
    }
}
