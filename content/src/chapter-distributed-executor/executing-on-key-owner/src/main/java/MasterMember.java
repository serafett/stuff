import com.hazelcast.core.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
public class MasterMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        Map<String, String> map = hzInstance.getMap("map");
        for (int k = 0; k < 10; k++)
            map.put(UUID.randomUUID().toString(), "");
        IExecutorService executor = hzInstance.getExecutorService("executor");
        for (String key : map.keySet())
            executor.executeOnKeyOwner(new VerifyTask(key), key);
    }
}
