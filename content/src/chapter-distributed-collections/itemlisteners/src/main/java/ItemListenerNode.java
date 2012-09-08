import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICollection;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;

public class ItemListenerNode {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = Hazelcast.getDefaultInstance();
        ICollection<String> queue = hazelcastInstance.getQueue("queue");
        queue.addItemListener(new ItemListenerImpl<String>(), true);
        System.out.println("ItemListener started");
    }

    private static class ItemListenerImpl<E> implements ItemListener<E> {
        @Override
        public void itemAdded(ItemEvent<E> itemEvent) {
            System.out.println("item added:" + itemEvent.getItem());
        }

        @Override
        public void itemRemoved(ItemEvent<E> itemEvent) {
            System.out.println("item removed:" + itemEvent.getItem());
        }
    }
}
