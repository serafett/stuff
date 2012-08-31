public class Main {

    public static final int INCREMENT_COUNT_PER_THREAD = 1000 * 1000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting");
        CounterService counterService = new CounterService();
        String id = counterService.create(0);

        Thread[] threads = new Thread[2];
        for (int k = 0; k < threads.length; k++)
            threads[k] = new IncrementThread(counterService, id);

        for (Thread thread : threads) thread.start();

        for (Thread thread : threads) thread.join();

        long actualCount = counterService.count(id);
        long expectedCount = INCREMENT_COUNT_PER_THREAD * threads.length;
        System.out.printf("Lost update detected: %s\n", (actualCount != expectedCount));
        System.out.printf("Expected count: %s\n", expectedCount);
        System.out.printf("Actual count: %s\n", actualCount);
    }

    static class IncrementThread extends Thread {
        private final CounterService counterService;
        private final String counterId;

        IncrementThread(CounterService counterService, String counterId) {
            this.counterService = counterService;
            this.counterId = counterId;
        }

        public void run() {
            for (int k = 0; k < INCREMENT_COUNT_PER_THREAD; k++)
                counterService.increment(counterId);
        }
    }
}
