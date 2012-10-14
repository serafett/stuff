import java.io.Serializable;

public class Person implements Serializable {
    public String name;
    public boolean man;
    public int age;

    public Person(String name, boolean active, int age) {
        this.man = active;
        this.age = age;
        this.name = name;
    }

      @Override
    public String toString() {
        return "Customer{" +
                "man=" + man +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}