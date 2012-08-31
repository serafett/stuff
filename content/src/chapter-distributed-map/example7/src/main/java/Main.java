import java.util.Set;

public class Main {
    public static void main(String[] args){
    	CustomerService customerService = new CustomerService();
    	customerService.create("Homer");

        Set<Customer> result = customerService.getWithName("Homer");
        System.out.println(result);
    }
}
