import java.io.Serializable;

public class Work implements Runnable, Serializable {
    private final String msg;

    public Work(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("echo:"+msg);
    }
}
