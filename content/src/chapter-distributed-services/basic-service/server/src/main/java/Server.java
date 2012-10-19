import com.hazelblast.server.*;
import com.hazelblast.server.pojoslice.*;
import com.hazelcast.core.*;
public class Server {
    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        Pojo pojo = new Pojo(hzInstance);
        Slice slice = new PojoSlice(pojo);
        SliceServer sliceServer = new SliceServer(slice);
        sliceServer.start();
    }
    public static class Pojo implements HazelcastInstanceProvider {
        @Exposed
        public final EmployeeService employeeService;
        private final HazelcastInstance hzInstance;

        public Pojo(HazelcastInstance hzInstance) {
            this.hzInstance = hzInstance;
            this.employeeService = new EmployeeServiceImpl(hzInstance);
        }
        public HazelcastInstance getHazelcastInstance() {
            return hzInstance;
        }
    }
}
