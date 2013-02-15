import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.config.QueueStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

public class StoreMember {

    public static void main(String[] args)throws Exception{
        QueueStoreConfig storeConfig = new QueueStoreConfig().setClassName(TheQueueStore.class.getName());
        QueueConfig queueConfig = new QueueConfig().setName("queue");
        queueConfig.setQueueStoreConfig(storeConfig);
        Config config = new Config().addQueueConfig(queueConfig);
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(config);
        IQueue<Long> queue = hzInstance.getQueue("queue");
        queue.put(System.currentTimeMillis());
        System.exit(0);
    }
}
