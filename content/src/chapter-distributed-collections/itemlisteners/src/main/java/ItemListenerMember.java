import com.hazelcast.core.*;
public class ItemListenerMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        ICollection<String> queue = hazelcastInstance.getQueue("queue");
        queue.addItemListener(new ItemListenerImpl<String>(), true);
        System.out.println("ItemListener started");
    }
    private static class ItemListenerImpl<E> implements ItemListener<E> {
        @Override
        public void itemAdded(ItemEvent<E> itemEvent) {
            System.out.println("Item added:" + itemEvent.getItem());
        }
        @Override
        public void itemRemoved(ItemEvent<E> itemEvent) {
            System.out.println("Item removed:" + itemEvent.getItem());
        }
    }
}
