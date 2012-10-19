import java.io.Serializable;
public class Person implements Serializable {
    public final String name;
    public final String id;
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String toString() {
        return String.format("Employee(id=%s,name=%s)",id,name);
    }
}