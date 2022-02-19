package org.dzmitry.kapachou.aop.throwing;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ValidationExceptionHandler {

    @AfterThrowing(value = "execution(* org.dzmitry.kapachou.aop.throwing.DummyValidatorProcessor.*(..) )", throwing = "exception")
    public void validateExceptionExecutionProcess(JoinPoint joinPoint, ValidationPasswordException exception) {
        log.info("===THROWING Aspect:===");
        String signature = joinPoint.getSignature().toShortString();
        log.info("STARTING {}", signature);
        log.warn("notification service sends a report to update the password {}", exception.getExpiredPassword());
        log.info("DONE {}", signature);
    }
}
