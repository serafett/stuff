import com.hazelcast.core.*;
public class IdGeneratorMember {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance hazelcast = Hazelcast.newHazelcastInstance(null);
        IdGenerator idGenerator = hazelcast.getIdGenerator("idGenerator");
        for (int k = 0; k < 10000; k++) {
            Thread.sleep(1000);
            System.out.printf("Id : %s\n", idGenerator.newId());
        }
    }
}
