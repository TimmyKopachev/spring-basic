package org.dzmitry.kapachou.aop.around;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogExecutionTimeHandler {

    @Around("@annotation(LogExecutionTime)")
    public void logTimeExecutionProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("===AROUND Aspect:===");
        String signature = joinPoint.getSignature().toShortString();
        log.info("STARTING {}", signature);

        long start = System.currentTimeMillis();
        joinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info("DONE {} in {}ms", signature, end - start);
    }
}
