package org.dzmitry.kapachou.mvc.service;

import lombok.AllArgsConstructor;
import org.dzmitry.kapachou.mvc.exception.EmployeeNotFoundException;
import org.dzmitry.kapachou.mvc.model.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.dzmitry.kapachou.mvc.model.EmployeePosition.MIDDLE;
import static org.dzmitry.kapachou.mvc.model.EmployeePosition.SENIOR;

@Service
@AllArgsConstructor
public class EmployeeService {

    final Map<Long, Employee> employeeDummyStorage = new ConcurrentHashMap<>();

    @PostConstruct
    public void setup() {
//        employeeDummyStorage.put(1L, new Employee("Dzmitry", SENIOR));
//        employeeDummyStorage.put(2L, new Employee("Viktor", SENIOR));
//        employeeDummyStorage.put(3L, new Employee("Roma", MIDDLE));
    }

    public Employee save(Employee employee) {
        return employeeDummyStorage.put(employee.getId(), employee);
    }

    public Employee update(Employee employee) {
        var e = employeeDummyStorage.get(employee.getId());
        if (e == null) {
            throw new EmployeeNotFoundException("Could not find employee %s with ID %s", employee.getName(), employee.getId());
        }
        return employeeDummyStorage.put(employee.getId(), e);
    }

    public Employee findById(Long id) {
        var e = employeeDummyStorage.get(id);
        if (e == null) {
            throw new EmployeeNotFoundException("Could not find employee %s with ID %s", "", id);
        }
        return e;
    }

    public Collection<Employee> findAll() {
        return employeeDummyStorage.values();
    }
}
