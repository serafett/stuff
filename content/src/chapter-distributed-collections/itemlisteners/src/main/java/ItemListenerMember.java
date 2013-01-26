import com.hazelcast.core.*;
public class ItemListenerMember {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        ICollection<String> queue = hzInstance.getQueue("queue");
        queue.addItemListener(new ItemListenerImpl<String>(), true);
        System.out.println("ItemListener started");
    }
    private static class ItemListenerImpl<E> implements ItemListener<E> {
        public void itemAdded(ItemEvent<E> itemEvent) {
            System.out.println("Item added:" + itemEvent.getItem());
        }
        public void itemRemoved(ItemEvent<E> itemEvent) {
            System.out.println("Item removed:" + itemEvent.getItem());
        }
    }
}
