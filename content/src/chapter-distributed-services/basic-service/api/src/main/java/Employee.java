import java.io.Serializable;
public class Employee implements Serializable {
    public final String name;
    public final String id;
    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String toString() {
        return String.format("Employee(id=%s,name=%s)",id,name);
    }
}