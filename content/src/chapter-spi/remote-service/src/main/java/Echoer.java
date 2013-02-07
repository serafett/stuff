import com.hazelcast.core.DistributedObject;
public interface Echoer extends DistributedObject {
    void echo(String routingId, String msg);
}
