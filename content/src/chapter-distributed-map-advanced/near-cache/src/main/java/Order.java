import java.io.Serializable;
import java.util.UUID;

public final class Order implements Serializable {
	private final String orderId = UUID.randomUUID().toString();
	private final String customerId;
	private final String articleId;
	private final int quantity;

	public Order(String customerId, String articleId, int quantity){
	    this.customerId = customerId;
	    this.articleId = articleId;
	    this.quantity = quantity;
    }
    public String getOrderId(){return orderId;}
    public String getCustomerId(){return customerId;}
    public String getArticleId(){return articleId;}
    public int getQuantity(){return quantity;}
}