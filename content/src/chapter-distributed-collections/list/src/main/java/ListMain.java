import com.hazelcast.core.Hazelcast;

import java.util.List;

public class ListMain {
    public static void main(String[] args)throws Exception{
        List<Integer> list = Hazelcast.getList("list");
        for(int k=0;k<10;k++){
            list.add(k);
        }
    }
}
