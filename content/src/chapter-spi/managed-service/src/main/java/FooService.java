import com.hazelcast.spi.*;
import java.util.Properties;
public class FooService implements ManagedService {
    public void init(NodeEngine nodeEngine, Properties properties) {
        System.out.println("FooService.init");
    }
    public void shutdown() {
        System.out.println("FooService.shutdown");
    }
}
