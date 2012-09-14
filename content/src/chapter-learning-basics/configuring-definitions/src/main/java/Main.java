import com.hazelcast.core.*;
public class Main {
    public static void main(String[] args)throws Exception{
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        IQueue q1 = hzInstance.getQueue("q");
        IQueue q2 = hzInstance.getQueue("q");
        q1.put("foo");
        System.out.println("q1==q2:"+(q1==q2));
        System.out.println("q1.size: "+q1.size());
        System.out.println("q2.size: "+q2.size());
        System.out.println("q1.name: "+q1.getId());
        System.out.println("q2.name: "+q2.getId());
    }
}
