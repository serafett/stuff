import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class Member {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
    }
}
