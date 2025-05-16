package org.dzmitry.kapachou.tx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
public class DataSourceProperties {

    private String url;
    private String host;
    private Integer port;
    private String username;
    private String dbName;
    private String password;
    private String driverClass;
    private String uniqueResourceName;

    @ConfigurationProperties(value = "database.setting.first")
    public static class FirstProperties extends DataSourceProperties {

    }

    @ConfigurationProperties(value = "database.setting.second")
    public static class SecondProperties extends DataSourceProperties {

    }


}
