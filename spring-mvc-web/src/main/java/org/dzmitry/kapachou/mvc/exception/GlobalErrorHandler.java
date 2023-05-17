package org.dzmitry.kapachou.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public @ResponseBody
    WarningMessage handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        return new WarningMessage("Mismatch data protocol", exception.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public @ResponseBody
    WarningMessage handleInternalFailure(EmployeeNotFoundException exception) {
        return new WarningMessage("System issue protocol", exception.getMessage());
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<List<String>> handleConstraintViolation(ConstraintViolationException exception) {
        List<String> warnings = new ArrayList<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            warnings.add(violation.getMessage());
        }

        return new ResponseEntity<>(warnings, HttpStatus.BAD_REQUEST);
    }

}
