import com.hazelcast.core.AtomicNumber;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ISemaphore;

public class SemaphoreNode {

    public static void main(String[] args)throws Exception{
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        ISemaphore semaphore = hazelcastInstance.getSemaphore("semaphore");
        AtomicNumber resource = hazelcastInstance.getAtomicNumber("resource");

        for(int k=0;k<1000;k++){
            System.out.println("At iteration: "+k);
            System.out.println("Active Threads: " + resource.get());
            semaphore.acquire();
            try{
                resource.incrementAndGet();
                Thread.sleep(1000);
                resource.decrementAndGet();
            }finally{
                semaphore.release();
            }
        }
        System.out.println("Finished");
    }
}
