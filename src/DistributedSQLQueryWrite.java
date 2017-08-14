import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;

import java.io.Serializable;
import java.util.Set;

public class DistributedSQLQueryWrite implements Serializable {

    public static void main(String[] args) {
        Config cfg = new Config();
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(cfg);
        IMap map = hz.getMap("employee");
        Employee employee = new Employee("Joe", 23, true, 40);
        map.put(0, employee);
        employee = new Employee("Ali", 54, true, 34);
        map.put(1, employee);
        employee = new Employee("Avi", 30, true, 64);
        map.put(2, employee);
    }
}