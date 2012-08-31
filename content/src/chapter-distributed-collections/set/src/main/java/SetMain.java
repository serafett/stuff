import com.hazelcast.core.Hazelcast;
import java.util.Set;

public class SetMain {
    public static void main(String[] args) {
        Set<Long> set = Hazelcast.getSet("set");
        for (int k = 0; k < 5; k++) {
            set.add(System.nanoTime());
        }

        System.out.println("size: " + set.size());
    }
}
