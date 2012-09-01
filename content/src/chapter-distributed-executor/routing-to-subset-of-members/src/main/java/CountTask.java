import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class CountTask implements Callable<Integer>, Serializable {

    @Override
    public Integer call() throws Exception {
        IMap<String, Integer> map = Hazelcast.getMap("map");
        int result = 0;
        for (String key : map.localKeySet()) {
            System.out.println("Calculating for key: "+key);
            result+=map.get(key);
        }
        System.out.println("Local Result: "+result);
        return result;
    }
}
