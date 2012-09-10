import com.hazelcast.client.ClientConfig;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

import java.io.Serializable;
import java.util.concurrent.Executor;

public class NativeClient {

    public static void main(String[] args){
        ClientConfig clientConfig = new ClientConfig();
        //clientConfig.getGroupConfig().setName("dev").setPassword("dev-pass");
        clientConfig.addAddress("10.37.129.2");

        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        Executor executor = client.getExecutorService();
        Runnable echoTask = new MyRunnable();
        executor.execute(echoTask);
        System.out.println("NativeClient finished!");
    }

    private static class MyRunnable implements Runnable, Serializable {
        @Override
        public void run() {
            System.out.println("echo");
        }
    }
}
