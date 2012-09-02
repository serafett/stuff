import com.hazelcast.core.Hazelcast;

public class SlaveNode {
    public static void main(String[] args){
        Hazelcast.getDefaultInstance();
    }
}
