package org.dzmitry.kapachou.tx.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionExecution;
import org.springframework.transaction.TransactionExecutionListener;

@Slf4j
@Component
public class TxProcessAudit implements TransactionExecutionListener {

    public void beforeBegin(TransactionExecution transaction) {
        log.info("tx - beforeBegin: {}", transaction.getTransactionName());
    }

    public void afterBegin(TransactionExecution transaction, @Nullable Throwable beginFailure) {
        log.info("tx - afterBegin: {}", transaction.getTransactionName());
    }

    public void beforeCommit(TransactionExecution transaction) {
        log.info("tx - beforeCommit: {}", transaction.getTransactionName());
    }

    public void afterCommit(TransactionExecution transaction, @Nullable Throwable commitFailure) {
        log.info("tx - afterCommit: {}", transaction.getTransactionName());
    }

    public void beforeRollback(TransactionExecution transaction) {
        log.info("tx - beforeRollback: {}", transaction.getTransactionName());
    }

    public void afterRollback(TransactionExecution transaction, @Nullable Throwable rollbackFailure) {
        log.info("tx - afterRollback: {}", transaction.getTransactionName());
    }
}
