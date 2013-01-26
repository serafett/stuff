import com.hazelcast.core.*;
import com.hazelcast.nio.DataSerializable;
import java.io.*;
import java.util.Map;
public class SerializationMember {
    public static void main(String[] args) {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        Map<String, Person> map = hzInstance.getMap("map");
        map.put("Peter", new Person("Peter", 37));
        Person p = map.get("Peter");
        System.out.println(p);
    }
    public static class Person implements DataSerializable {
        private String name;
        private int age;
        private Person(){}
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public void writeData(DataOutput out) throws IOException {
            out.writeUTF(name);
            out.writeInt(age);
        }
        public void readData(DataInput in) throws IOException {
            this.name = in.readUTF();
            this.age = in.readInt();
        }
        public String toString() {
            return String.format("Person(name=%s,age=%s)",name,age);
        }
    }
}

