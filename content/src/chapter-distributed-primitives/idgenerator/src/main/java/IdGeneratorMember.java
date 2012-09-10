import com.hazelcast.core.*;
public class IdGeneratorMember {
    public static void main(String[] args) {
        HazelcastInstance hazelcast = Hazelcast.getDefaultInstance();
        IdGenerator idGenerator = hazelcast.getIdGenerator("idGenerator");
        for (int k = 0; k < 1000000; k++) {
            long id = idGenerator.newId();
            System.out.printf("Id : %s\n", id);
        }
    }
}
