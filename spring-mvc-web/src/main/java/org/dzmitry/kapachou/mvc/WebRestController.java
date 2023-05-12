package org.dzmitry.kapachou.mvc;

import lombok.AllArgsConstructor;
import org.dzmitry.kapachou.mvc.model.Employee;
import org.dzmitry.kapachou.mvc.service.EmployeeService;
import org.dzmitry.kapachou.mvc.validation.EmployeeValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@Validated
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
    public void createEmployee(@RequestBody @EmployeeValidation List<@Valid Employee> employees) {
        employees.forEach(employeeService::save);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }
}
