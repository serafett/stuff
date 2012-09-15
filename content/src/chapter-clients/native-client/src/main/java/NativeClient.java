import com.hazelcast.client.*;
import com.hazelcast.core.HazelcastInstance;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public class NativeClient {
    public static void main(String[] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("127.0.0.1");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        //BlockingQueue<String> queue = client.getQueue("queue");
        //queue.put("Hello!");
        System.out.println("Message send by lite member!");

        Executor e = client.getExecutorService();
        e.execute(new Runnable(){
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
