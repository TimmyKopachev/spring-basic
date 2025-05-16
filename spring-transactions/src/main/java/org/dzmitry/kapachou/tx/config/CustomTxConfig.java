package org.dzmitry.kapachou.tx.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.kapachou.tx.service.TxProcessAudit;
import org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;

import java.util.Properties;


@Configuration
@Slf4j
@RequiredArgsConstructor
@EnableTransactionManagement
//@DependsOn("atomikosTransactionManager")
public class CustomTxConfig {


    public LocalContainerEntityManagerFactoryBean initLocalContainerEntityManagerFactory(
            DataSource dataSource) {
        var localEntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        localEntityManagerFactory.setJtaDataSource(dataSource);
        localEntityManagerFactory.setPackagesToScan("org.dzmitry");
        localEntityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter(dataSource));
        return localEntityManagerFactory;
    }

    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        var info = new MutablePersistenceUnitInfo();
        info.setJtaDataSource(dataSource);
        info.setTransactionType(PersistenceUnitTransactionType.JTA);
        vendorAdapter.getJpaPropertyMap(info);
        return vendorAdapter;
    }

    protected Properties getProperties(DataSourceProperties settings) {
        Properties properties = new Properties();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("jakarta.persistence.transactionType", "JTA");
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        return properties;
    }


    @EnableJpaRepositories(
            basePackages = "org.dzmitry.kapachou.tx.service.first",
            entityManagerFactoryRef = "firstEntityManagerFactory",
            transactionManagerRef = "firstTransactionManager"
    )
    static class FirstTxConfig extends CustomTxConfig{

        @Bean(name = "firstEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean firstXADataSource(
                @Qualifier("firstXADataSource") DataSource dataSource,
                DataSourceProperties.FirstProperties settings) {
            var em = initLocalContainerEntityManagerFactory(dataSource);
            em.setPersistenceUnitName("first-pre-pr-db");
            em.setJpaProperties(getProperties(settings));
            return em;
        }


        @Bean("firstTransactionManager")
        public PlatformTransactionManager firstTransactionManager(
                @Qualifier("firstEntityManagerFactory") EntityManagerFactory emf) {
            var jpaTxManager = new JpaTransactionManager(emf);
            jpaTxManager.getTransactionExecutionListeners().add(new TxProcessAudit());
            return jpaTxManager;
        }

    }

    @EnableJpaRepositories(
            basePackages = "org.dzmitry.kapachou.tx.service.second",
            entityManagerFactoryRef = "secondEntityManagerFactory",
            transactionManagerRef = "secondTransactionManager"
    )
    static class SecondTxConfig extends CustomTxConfig{

        @Bean(name = "secondEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean secondXADataSource(
                @Qualifier("secondXADataSource") DataSource dataSource,
                DataSourceProperties.SecondProperties settings) {
            var em = initLocalContainerEntityManagerFactory(dataSource);
            em.setPersistenceUnitName("second-post-pr-db");
            em.setJpaProperties(getProperties(settings));
            return em;
        }


        @Bean(name = "secondTransactionManager")
        public PlatformTransactionManager transactionManager(
                @Qualifier("secondEntityManagerFactory") EntityManagerFactory emf) {
            var jpaTxManager = new JpaTransactionManager(emf);
            jpaTxManager.getTransactionExecutionListeners().add(new TxProcessAudit());
            jpaTxManager.setValidateExistingTransaction(true);
            jpaTxManager.setTransactionSynchronization(1);
            return jpaTxManager;
        }
    }

}
