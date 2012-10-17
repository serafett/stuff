import com.hazelcast.core.MapStore;
import java.sql.*;
import java.util.*;
import static java.lang.String.format;
public class PersonMapStore implements MapStore<Long, Person> {
    private final Connection con;
    public PersonMapStore() {
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:mydatabase", "SA", "");
            con.createStatement().executeUpdate(
                    "create table if not exists person (id bigint, name varchar(45))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(Long key) {
        try {
            con.createStatement().executeUpdate(format("delete from person where id = %s", key));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void store(Long key, Person value) {
        try {
            con.createStatement().executeUpdate(format("insert into person values(%s,'%s')", key, value.name));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void storeAll(Map<Long, Person> map) {
        for (Map.Entry<Long, Person> entry : map.entrySet())
            store(entry.getKey(), entry.getValue());
    }
    public void deleteAll(Collection<Long> keys) {
       for(Long key: keys) delete(key);
    }
    public Person load(Long key) {
        try {
            ResultSet resultSet = con.createStatement().executeQuery(
                    format("select name from person where id =%s", key));
            try {
                if (!resultSet.next())return null;
                String name = resultSet.getString(1);
                return new Person(name);
            } finally {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Map<Long, Person> loadAll(Collection<Long> keys) {
        Map<Long, Person> result = new HashMap<Long, Person>();
        for (Long key : keys) result.put(key, load(key));
        return result;
    }
    public Set<Long> loadAllKeys() {
         try {
            ResultSet resultSet = con.createStatement().executeQuery(
                    "select id from person");
            try {
                Set<Long> keys = new HashSet<Long>();
                while (resultSet.next())
                    keys.add(resultSet.getLong(1));
                return keys;
            } finally {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
