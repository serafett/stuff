public class Main {

    public static void main(String[] args){
        OrderService orderService = new OrderService();
        orderService.createArticle("jam");

        System.out.println("Starting");
        long startMs = System.currentTimeMillis();
        for(int k=0;k<200000;k++){
            orderService.placeOrder("peter","jam",1);
        }
        long durationMs = System.currentTimeMillis()-startMs;
        System.out.printf("Finished in %s ms\n", durationMs);
    }
}
