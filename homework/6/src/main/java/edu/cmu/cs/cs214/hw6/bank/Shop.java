package edu.cmu.cs.cs214.hw6.bank;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ckaestne on 3/23/2017.
 */
public class Shop implements AccountOwner {
    private final Set<Person> employees = new HashSet<>();
    private boolean isClosed = false;

    public synchronized void addEmployee(Person p) {
        assert p.getEmployer() == null;
        employees.add(p);
        p.setEmployer(this);
    }

    public synchronized void removeEmployee(Person p) {
        employees.remove(p);
        p.setEmployer(null);
    }

    public Set<Person> getEmployees() {
        return employees;
    }

    public void close() {
        isClosed = true;
        //fire all employees
        for (Person p : employees)
            p.setEmployer(null);
        employees.clear();
    }

    public boolean isClosed() {
        return isClosed;
    }
}
