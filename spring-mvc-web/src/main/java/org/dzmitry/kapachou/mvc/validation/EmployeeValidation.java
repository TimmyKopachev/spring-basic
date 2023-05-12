package org.dzmitry.kapachou.mvc.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//@Target(ElementType.TYPE)
@Constraint(validatedBy = EmployeeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmployeeValidation {
    String message() default "Employee has invalidated values";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
