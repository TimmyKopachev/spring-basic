package org.dzmitry.kapachou.tx.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.net.SocketFactory;
import javax.sql.DataSource;
import java.net.SocketImplFactory;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties({
        DataSourceProperties.FirstProperties.class,
        DataSourceProperties.SecondProperties.class
})
public class CustomDatabaseConfig {

    protected DataSource initXADataSource(DataSourceProperties settings) {
        SQLServerXADataSource xaDataSource = new SQLServerXADataSource();
        xaDataSource.setURL(settings.getUrl());
        xaDataSource.setServerName(settings.getHost());
        xaDataSource.setPortNumber(settings.getPort());
        xaDataSource.setDatabaseName(settings.getDbName());
        xaDataSource.setUser(settings.getUsername());
        xaDataSource.setPassword(settings.getPassword());
        xaDataSource.setEncrypt(Boolean.FALSE.toString());
        xaDataSource.setTrustServerCertificate(true);

        AtomikosDataSourceBean atomikosDataSource = new AtomikosDataSourceBean();
        atomikosDataSource.setXaDataSource(xaDataSource);
        atomikosDataSource.setXaDataSourceClassName(settings.getDriverClass());
        atomikosDataSource.setUniqueResourceName(settings.getUniqueResourceName());
        atomikosDataSource.setXaProperties(atomikosProperties(settings));

        return atomikosDataSource;
    }

    protected Properties atomikosProperties(DataSourceProperties settings) {
        Properties xaProperties = new Properties();
        xaProperties.put("user", settings.getUsername());
        xaProperties.put("password", settings.getPassword());
        xaProperties.put("databaseName", settings.getDbName());
        xaProperties.put("serverName", settings.getHost());
        xaProperties.put("portNumber", settings.getPort().toString());
        return xaProperties;
    }

    @Primary
    @Bean(name = "firstXADataSource")
    public DataSource firstDataSource(DataSourceProperties.FirstProperties settings) {
        return initXADataSource(settings);
    }

    @Bean(name = "secondXADataSource")
    public DataSource secondDataSource(DataSourceProperties.SecondProperties settings) {
        return initXADataSource(settings);
    }


    @Bean
    public SpringLiquibase firstLiquibase(
            @Qualifier("firstXADataSource") DataSource firstDataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(firstDataSource);
        liquibase.setChangeLog("classpath:liquibase-schema/liquibase-changelog.xml");
        liquibase.setContexts("first");
        return liquibase;
    }

    @Bean
    public SpringLiquibase secondLiquibase(
            @Qualifier("secondXADataSource") DataSource secondDataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(secondDataSource);
        liquibase.setChangeLog("classpath:liquibase-schema/liquibase-changelog.xml");
        liquibase.setContexts("second");
        return liquibase;
    }

}
