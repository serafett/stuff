import com.hazelcast.core.Hazelcast;

import java.util.Map;

public class OrderService {
    private final Map<OrderKey, Order> orderMap = Hazelcast.getMap("orders");
    private final Map<String, Article> articleMap = Hazelcast.getMap("articles");

    public String createArticle(String articleName) {
        Article article = new Article("jam");
        articleMap.put(articleName, article);
        return articleName;
    }

    public String placeOrder(String customerId, String articleId, int quantity) {
        Article article = articleMap.get(articleId);
        if (article == null) throw new IllegalArgumentException();

        Order order = new Order(customerId, articleId, quantity);
        OrderKey key = new OrderKey(order.getOrderId(), customerId);
        orderMap.put(key, order);
        return order.getOrderId();
    }
}