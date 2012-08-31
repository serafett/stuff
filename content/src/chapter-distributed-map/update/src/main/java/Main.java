public class Main {

    public static void main(String[] args){
    	CustomerService customerService = new CustomerService();
    	String id = customerService.create("Homer");
        customerService.updateName(id, "Bart");
    }
}
