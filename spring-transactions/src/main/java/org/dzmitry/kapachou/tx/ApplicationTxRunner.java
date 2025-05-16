package org.dzmitry.kapachou.tx;


import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(
        scanBasePackages="org.dzmitry.kapachou",
        exclude = {
            DataSourceAutoConfiguration.class,
            DataSourceTransactionManagerAutoConfiguration.class,
            HibernateJpaAutoConfiguration.class
})
public class ApplicationTxRunner {


    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ApplicationTxRunner.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
