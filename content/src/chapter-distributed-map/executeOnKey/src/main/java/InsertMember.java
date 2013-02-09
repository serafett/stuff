import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Date;

public class InsertMember {

    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        IMap map = hzInstance.getMap("theMap");
        map.put("peter", new Date().toString());
        System.out.println("Finished");
    }
}