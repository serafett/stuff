
public class Address {
    private String street;
    private String state;

    public Address(String state, String street) {
       this.state = state;
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }
}
