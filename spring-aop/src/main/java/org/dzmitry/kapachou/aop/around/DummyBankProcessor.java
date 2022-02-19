package org.dzmitry.kapachou.aop.around;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class DummyBankProcessor {

    private double invoice = 1000;

    private final double interestRate = 0.02;
    private final double expectedIncome = 3000000;


    /**
     * I have an invoice = 1000
     * and I intend to put the budget to a bank with interest rate = 2%
     * how many months need to wait for expected 3 millions
     */
    @EventListener(ApplicationReadyEvent.class)
    @LogExecutionTime
    public void process() {
        int month = 1;
        while (invoice < expectedIncome) {
            this.invoice += (invoice * interestRate);
            month++;
        }
        log.info("{} months need to wait if you want to achieve {} with {} with starting {}", month, expectedIncome, interestRate, invoice);
    }

}
