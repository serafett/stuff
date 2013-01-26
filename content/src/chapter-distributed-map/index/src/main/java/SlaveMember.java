import com.hazelcast.core.Hazelcast;

//If you want to test in a distributed setting, just start a bunch of slave members. Hazelcast takes
//care of moving partitions to these slaves.
public class SlaveMember {
    public static void main(String[] args){
        Hazelcast.newHazelcastInstance();
    }
}
