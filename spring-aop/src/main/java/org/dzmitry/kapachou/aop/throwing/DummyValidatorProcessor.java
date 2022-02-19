package org.dzmitry.kapachou.aop.throwing;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class DummyValidatorProcessor {

    private final String password = "password@123";

    @EventListener(ApplicationReadyEvent.class)
    public void process() {
        throw new ValidationPasswordException(this.password);
    }
}
