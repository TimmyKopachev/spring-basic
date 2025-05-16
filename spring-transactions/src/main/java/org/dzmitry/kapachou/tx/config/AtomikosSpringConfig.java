package org.dzmitry.kapachou.tx.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class AtomikosSpringConfig {

    @Bean
    public UserTransactionManager atomikosTransactionManager() {
        UserTransactionManager utm = new UserTransactionManager();
        utm.setForceShutdown(true);
        return utm;
    }

    @Bean
    public UserTransactionImp atomikosUserTransaction() {
        return new UserTransactionImp();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(UserTransactionManager utm, UserTransactionImp uti) {
        return new JtaTransactionManager(uti, utm);
    }
}
