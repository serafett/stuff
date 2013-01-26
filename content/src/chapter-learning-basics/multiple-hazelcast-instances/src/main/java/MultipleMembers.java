import com.hazelcast.core.*;
public class MultipleMembers {
    public static void main(String[] args){
        HazelcastInstance hzInstance1 = Hazelcast.newHazelcastInstance();
        HazelcastInstance hzInstance2 = Hazelcast.newHazelcastInstance();
    }
}
