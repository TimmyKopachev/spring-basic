package org.dzmitry.kapachou.mvc.validation;

import org.dzmitry.kapachou.mvc.model.Employee;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.IntStream;

public class EmployeeConstraintValidator implements ConstraintValidator<EmployeeValidation, List<Employee>> {
    @Override
    public boolean isValid(List<Employee> employees, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        IntStream.range(0, employees.size())
                .filter(i -> !employees.get(i).getName().equalsIgnoreCase("X"))
                .forEach(i ->
                        context.buildConstraintViolationWithTemplate("Employee: " + employees.get(i).getName() + " is not X!")
                                .addConstraintViolation());

        return employees.stream().allMatch(e -> e.getName().equalsIgnoreCase("X"));
    }
}
