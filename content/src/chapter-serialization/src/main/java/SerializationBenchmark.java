import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.nio.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Map;

public class SerializationBenchmark {

    private final HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
    private final Map map = hzInstance.getMap("map");

    public static void main(String[] args) {
        SerializationBenchmark test = new SerializationBenchmark();
        test.run();
    }

    private void run() {
        run("serializable", new SerializableObject());
        run("serializable with version", new SerializableWithVersionIdObject());
        run("externizable", new ExternalizableObject());
        run("data serializable", new DataSerializableObject());
    }

    private void run(String name, Object o) {
        int count = 1000 * 1000;
        String key = "key";
        long startMs = System.currentTimeMillis();
        for (int k = 0; k < count; k++) {
            map.put(key, o);
            map.get(key);
        }
        long durationMs = System.currentTimeMillis() - startMs;
        double performance = (1000d * count) / durationMs;
        System.out.printf(name + " Performance = %s write/reads per second\n", performance);
    }

    public static class SerializableObject implements Serializable {
        private int id;
    }

    public static class SerializableWithVersionIdObject implements Serializable {
        private static final long serialVersionUID = 1;
        private int id;
    }

    static public class ExternalizableObject implements Externalizable {
        private int id;

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            id = in.read();
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.write(id);
        }
    }

    static public class DataSerializableObject implements DataSerializable {
        private int id;

        //todo: should not be needed
        public DataSerializableObject() {
        }

        @Override
        public void readData(DataInput in) throws IOException {
            id = in.readInt();
        }

        @Override
        public void writeData(DataOutput out) throws IOException {
            out.writeInt(id);
        }
    }
}
