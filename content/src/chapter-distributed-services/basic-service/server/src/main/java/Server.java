import com.hazelblast.server.Slice;
import com.hazelblast.server.SliceServer;
import com.hazelblast.server.pojoslice.PojoSlice;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class Server {

    public static void main(String[] args){
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
        Pojo pojo = new Pojo(hzInstance);
        Slice slice = new PojoSlice(pojo);
        SliceServer sliceServer = new SliceServer(slice);
        sliceServer.start();
    }
}
