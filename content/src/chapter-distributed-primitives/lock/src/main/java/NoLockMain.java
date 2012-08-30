import com.hazelcast.core.AtomicNumber;
import com.hazelcast.core.Hazelcast;

public class NoLockMain {
    public static void main(String[] args) throws InterruptedException {
        AtomicNumber number1 = Hazelcast.getAtomicNumber("number1");
        AtomicNumber number2 = Hazelcast.getAtomicNumber("number2");
        System.out.println("Started");
        for (int k = 0; k < 1000000; k++) {
            if (k % 10000 == 0) System.out.println("at: " + k);
            if (k % 2 == 0) {
                long n1 = number1.get();
                Thread.sleep(100);
                long n2 = number2.get();
                if (n1 - n2 != 0) System.out.println("Difference detected!");
            } else {
                number1.incrementAndGet();
                number2.incrementAndGet();
            }
        }
        System.out.println("Finished");
    }
}