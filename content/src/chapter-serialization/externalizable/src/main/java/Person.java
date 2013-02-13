import java.io.*;

public class Person implements Externalizable {
    private String name;
    public Person(String name) {
        this.name = name;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
     }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
       out.writeUTF(name);
    }

    @Override
    public String toString() {
        return String.format("Person(name=%s)",name);
    }
}
