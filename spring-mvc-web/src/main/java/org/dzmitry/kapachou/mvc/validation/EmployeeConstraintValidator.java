package org.dzmitry.kapachou.mvc.validation;

import org.dzmitry.kapachou.mvc.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class EmployeeConstraintValidator implements ConstraintValidator<EmployeeValidation, List<Employee>> {
    @Override
    public boolean isValid(List<Employee> employees, ConstraintValidatorContext constraintValidatorContext) {
        return employees.stream().allMatch(e -> StringUtils.hasText("X"));
    }
}
