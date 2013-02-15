import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.TypeSerializer;

import java.io.IOException;

public class PersonTypeSerializer implements TypeSerializer<Person> {

    @Override
    public void destroy() {
    }

    @Override
    public int getTypeId() {
        return PortableFactoryImpl.PERSON_CLASS_ID;
    }

    @Override
    public void write(ObjectDataOutput out, Person object) throws IOException {
        out.writeUTF(object.getName());
    }

    @Override
    public Person read(ObjectDataInput in) throws IOException {
        return new Person(in.readUTF());
    }
}
