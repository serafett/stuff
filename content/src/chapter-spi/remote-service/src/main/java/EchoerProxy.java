import com.hazelcast.spi.*;
import java.util.concurrent.*;
public class EchoerProxy implements Echoer {
    private final NodeEngine nodeEngine;
    private final String objectId;
    public EchoerProxy(String objectId, NodeEngine nodeEngine) {
        this.nodeEngine = nodeEngine;
        this.objectId = objectId;
    }
    public Object getId() {return objectId;}
    public String getName() {
        return null;
    }
    public void echo(String routingId, String msg) {
        EchoOperation operation = new EchoOperation();
        int partitionId = nodeEngine.getPartitionService().getPartitionId(routingId);
        InvocationBuilder builder = nodeEngine.getOperationService()
                .createInvocationBuilder("FooService",operation,partitionId);
        try {
            builder.build().invoke().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    static class EchoOperation extends AbstractOperation {
        public void run() throws Exception {
            System.out.println("Echo");
        }
    }
    public void destroy() {
    }
}
