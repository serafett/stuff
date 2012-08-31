import java.io.Serializable;
import java.util.UUID;

public class Counter implements Serializable {
    private final String id = UUID.randomUUID().toString();
    private long value;

    public Counter(long value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setValue(long newValue) {
        this.value = newValue;
    }

    public void inc() {
        this.value++;
    }

    public long getValue() {
        return value;
    }
}