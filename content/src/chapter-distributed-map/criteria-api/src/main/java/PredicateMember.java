import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Expression;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.Predicates;
import com.hazelcast.query.SqlPredicate;

import java.util.HashSet;
import java.util.Set;

import static com.hazelcast.query.Predicates.and;
import static com.hazelcast.query.Predicates.between;
import static com.hazelcast.query.Predicates.equal;
import static com.hazelcast.query.Predicates.get;
import static com.hazelcast.query.Predicates.not;
import static com.hazelcast.query.Predicates.or;

public class PredicateMember {

    private HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(null);
    private IMap<String, Person> personMap = hzInstance.getMap("personMap");


    public static void main(String[] args) {
        new PredicateMember().run();
    }

    public void run() {
        personMap.put("1", new Person("peter", false, 36));
        //personMap.put("2", new Customer("talip", true, 36));
        //personMap.put("3", new Customer(null, true, 36));



        Expression expr = get("age");
        Predicate predicate = new SqlPredicate("NOT male");// between(expr, 36, 36);

        System.out.println("name.size=5:");
        for (Person c : personMap.values(predicate)) {
            System.out.println(c);
        }

        //Set<Customer> employees = getWithName("peter");
        //System.out.println("Employees:" + employees);
    }

    public Set<Person> getWithNameNaive(String name){
        Set<Person> result = new HashSet<Person>();
        for(Person person: personMap.values()){
            if(person.name.equals(name)){
                result.add(person);
            }
        }

        return result;
    }

    public Set<Person> getNotWithName(String name) {
        Predicate namePredicate = equal(get("name"), name);
        Predicate predicate = not(namePredicate);
        return (Set<Person>) personMap.values(predicate);
    }

    public Set<Person> getWithName(String name) {
        Expression getNameExpression = get("name");
        Predicate predicate = equal(getNameExpression, name);
        return (Set<Person>) personMap.values(predicate);
    }

    public Set<Person> getWithNameAndAge(String name, int age) {
        Predicate namePredicate = equal(get("name"), name);
        Predicate agePredicate = equal(get("age"), age);
        Predicate predicate = and(namePredicate, agePredicate);
        return (Set<Person>) personMap.values(predicate);
    }

    public Set<Person> getWithNameAndAgeSimplified(String name, int age) {
        EntryObject e = new PredicateBuilder().getEntryObject();
        Predicate predicate = e.get("name").equal(name).and(e.get("age").equal(age));
        return (Set<Person>) personMap.values(predicate);
    }

    public Set<Person> getWithNameOrAge(String name, int age) {
        Predicate namePredicate = equal(get("name"), name);
        Predicate agePredicate = equal(get("age"), age);
        Predicate predicate = or(namePredicate, agePredicate);
        return (Set<Person>) personMap.values(predicate);
    }
}
