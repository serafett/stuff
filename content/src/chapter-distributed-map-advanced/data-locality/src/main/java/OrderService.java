import com.hazelcast.core.Hazelcast;

import java.util.Map;

public class OrderService {
    private final Map<OrderKey, Order> orderMap = Hazelcast.getMap("orders");

    public String placeOrder(String customerId, String articleId, int quantity) {
        Order order = new Order(customerId, articleId, quantity);
        OrderKey key = new OrderKey(order.getOrderId(), customerId);
        orderMap.put(key, order);

        System.out.printf("Order with id %s created: customer: %s, articleId %s, quantity %s\n",
                order.getOrderId(), customerId, articleId, quantity);

        return order.getOrderId();
    }
}