import com.hazelcast.core.*;
public class Member {
    public static void main(String[] args) {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        Echoer echoer = (Echoer)instance.getDistributedObject("FooService","bla");
        echoer.echo("bla","bla");
    }
}
