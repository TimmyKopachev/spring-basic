package org.dzmitry.kapachou.mvc;

import lombok.AllArgsConstructor;
import org.dzmitry.kapachou.mvc.model.Employee;
import org.dzmitry.kapachou.mvc.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("employees")
@AllArgsConstructor
public class WebRestController {

    final EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping
    @ResponseStatus
    public Collection<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }
}
