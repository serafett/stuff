import java.io.Serializable;
public class Person implements Serializable {
    public String name;
    public Person(String name) {
        this.name = name;
    }
    public String toString() {
        return "Person{name='" + name + "'}";
    }
}