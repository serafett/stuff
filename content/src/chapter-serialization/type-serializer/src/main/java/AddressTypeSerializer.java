import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.TypeSerializer;

import java.io.IOException;

public class AddressTypeSerializer implements TypeSerializer<Address> {

    @Override
    public void destroy() {
    }

    @Override
    public int getTypeId() {
        return PortableFactoryImpl.ADDRESS_CLASS_ID;
    }

    @Override
    public void write(ObjectDataOutput out, Address object) throws IOException {
        out.writeUTF(object.getState());
        out.writeUTF(object.getStreet());
    }

    @Override
    public Address read(ObjectDataInput in) throws IOException {
        return new Address(in.readUTF(), in.readUTF());
    }
}
