package org.dzmitry.kapachou.mvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
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

}
