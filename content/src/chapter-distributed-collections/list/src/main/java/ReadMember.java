import com.hazelcast.config.Config;
import com.hazelcast.config.ExecutorConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;

public class ReadMember {
    public static void main(String[] args) {
        ExecutorConfig executorConfig = new ExecutorConfig().setPoolSize(10);
        Config config = new Config().addExecutorConfig(executorConfig);
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        IList<String> list = hzInstance.getList("list");
        for (String s : list)
            System.out.println(s);
        System.out.println("Reading finished!");
    }
}
