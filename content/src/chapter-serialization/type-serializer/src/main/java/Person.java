import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import java.io.IOException;
public class Person implements Portable, HazelcastInstanceAware {
    private String name;
    private int age;
    private HazelcastInstance hzInstance;

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hzInstance = hazelcastInstance;
    }

    Person(){}
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public int getClassId() {
        return PortableFactoryImpl.PERSON_CLASS_ID;
    }
    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        System.out.println("Serialize");
        writer.writeUTF("name", name);
        System.out.println("hzInstance is null: "+(hzInstance==null));
        writer.writeInt("age", age);
    }
    @Override
    public void readPortable(PortableReader reader) throws IOException {
        System.out.println("Deserialize");
        this.name = reader.readUTF("name");
        this.age = reader.readInt("age");
    }
    @Override
    public String toString() {
        return String.format("Person(name=%s,age=%s)",name,age);
    }
}
