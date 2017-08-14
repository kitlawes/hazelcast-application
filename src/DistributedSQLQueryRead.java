import java.io.Serializable;
import java.util.Set;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.SqlPredicate;

public class DistributedSQLQueryRead implements Serializable {

    public static void main(String[] args) {
        Config cfg = new Config();
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(cfg);
        IMap map = hz.getMap("employee");

        Set<Employee> employees = (Set<Employee>) map.values(new SqlPredicate("active AND age < 40"));
        for (Employee employee : employees) {
            System.out.println(employee.getName() + "," + employee.getAge() + "," + employee.isActive() + "," + employee.getSalary());
        }

        EntryObject e = new PredicateBuilder().getEntryObject();
        Predicate predicate = e.is("active").and(e.get("age").lessThan(40));
        employees = (Set<Employee>) map.values(predicate);
        System.out.println();
        for (Employee employee : employees) {
            System.out.println(employee.getName() + "," + employee.getAge() + "," + employee.isActive() + "," + employee.getSalary());
        }
    }
}