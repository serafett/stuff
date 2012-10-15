import java.io.Serializable;

public class Person implements Serializable {
    public String name;
    public boolean male;
    public int age;
    public Address address = new Address();

    public Person(String name, boolean male, int age) {
        this.male = male;
        this.age = age;
        this.name = name;
    }

      @Override
    public String toString() {
        return "Person{" +
                "male=" + male +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}