import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Transaction;

import java.util.Map;

public class TransactionTemplateExample {

    public static void main(String[] args) throws Throwable {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        final Map map = hzInstance.getMap("map");

        new TransactionTemplate<String>(){
            @Override
            public String call(HazelcastInstance hzInstance) {
                map.put("foo","bar");
                return null;
            }
        }.execute(hzInstance);
    }

    static abstract class TransactionTemplate<E> {

        public abstract E call(HazelcastInstance hzInstance);

        public E execute(HazelcastInstance hzInstance) {
            Transaction txn = hzInstance.getTransaction();
            boolean freshTx = txn.getStatus() == Transaction.TXN_STATUS_NO_TXN;
            boolean success = false;
            if(freshTx) txn.begin();

            try {
                E result = call(hzInstance);
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
