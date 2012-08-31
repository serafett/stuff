import java.io.Serializable;
import java.util.UUID;

public class Counter implements Serializable {
    private final String id;
    private long version;
    private long value;

    public Counter(long value) {
        this(UUID.randomUUID().toString(), value, 0);
    }

    public Counter(String id, long value, long version) {
        this.id = id;
        this.value = value;
        this.version = version;
    }

    public String getId() {return id;}

    public void inc() {value++;}

    public long getValue() {return value;}

    public void incVersion() {version++;}

    public long getVersion() { return version;}

    //TODO: Is equals/hash needed at all for the 'imap.replace' method since it works based on byte-arrays
    //instead of the actual objects.
    public boolean equals(Object thatObject) {
        if (thatObject == this) return true;
        if (!(thatObject instanceof Counter)) return false;
        Counter that = (Counter) thatObject;
        if(!(that.id.equals(this.id)))return false;
        if(that.version!=this.version)return false;
        //if(that.value!=this.value)return false;
        return true;
    }

    public int hashCode() {
        int hash = id.hashCode();
        hash = 31*hash + hashCode(version);
        //hash = 31*hash + hashCode(value);
        return hash;
    }

    static int hashCode(long l){return ((int)(l ^ (l >>> 32)));}
}