import java.io.Serializable;

public class EchoTask implements Runnable,Serializable{
    private final String msg;
    public EchoTask(String msg) {
        this.msg = msg;
    }
    @Override
    public void run() {
        System.out.println(msg);
    }
}
