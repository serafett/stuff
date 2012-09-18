import java.io.Serializable;
import java.util.UUID;
import static java.lang.String.format;

public class Customer implements Serializable {
  private final String id = UUID.randomUUID().toString();
  private String name;
  public Customer(String name){this.name = name;}
  public String getId(){return id;}
  public String getName(){return name;}
  public void setName(String newName){this.name = newName;}
  public String toString(){
      return format("Customer(id=%s,name=%s)", id, name);
  }
}