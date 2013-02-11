import com.hazelcast.core.*;
public class SemaphoreMember {
    public static void main(String[] args)throws Exception{
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        ISemaphore semaphore = hzInstance.getSemaphore("semaphore");
        AtomicNumber resource = hzInstance.getAtomicNumber("resource");
        for(int k=0;k<1000;k++){
            System.out.println("At iteration: "+k +", Active Threads: " + resource.get());
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
