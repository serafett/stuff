import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Transaction;

import java.util.Map;

public class TransactionTemplateExample {

    public static void main(String[] args) throws Throwable {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        final Map map = hz.getMap("map");

        new TransactionTemplate<String>() {
            @Override
            public String call(HazelcastInstance hz) {
                map.put("foo", "bar");
                return null;
            }
        }.execute(hz);
    }

    static abstract class TransactionTemplate<E> {

        public abstract E call(HazelcastInstance hz);

        public E execute(HazelcastInstance hz) {
            Transaction txn = hz.getTransaction();
            boolean freshTx = txn.getStatus() == Transaction.TXN_STATUS_NO_TXN;
            boolean success = false;
            if (freshTx) txn.begin();

            try {
                E result = call(hz);
                success = true;
                return result;
            } finally {
                if (freshTx) {
                    if (success) txn.commit();
                    else txn.rollback();
                }
            }
        }
    }

}
