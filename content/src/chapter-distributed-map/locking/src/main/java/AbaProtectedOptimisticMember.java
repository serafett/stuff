import com.hazelcast.core.*;
import java.io.Serializable;
public class AbaProtectedOptimisticMember {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        IMap<String, Value> map = hzInstance.getMap("map");
        String key = "1";
        map.put(key, new Value());
        System.out.println("Starting");
        for (int k = 0; k < 1000; k++) {
            for (; ; ) {
                Value oldValue = map.get(key);
                Value newValue = new Value(oldValue);
                Thread.sleep(10);
                newValue.field++;
                newValue.version++;
                if(map.replace(key, oldValue, newValue))
                    break;
            }
        }
        System.out.println("Finished! Result = " + map.get(key).field);
    }
    static class Value implements Serializable {
        public int field;
        public int version;
        public Value(){}
        public Value(Value that) {
            this.field = that.field;
            this.version = that.version;
        }
        public boolean equals(Object o){
            if(o == this)return true;
            if(!(o instanceof Value))return false;
            Value that  = (Value)o;
            return that.field == this.field && this.version==that.version;
        }
    }
}
