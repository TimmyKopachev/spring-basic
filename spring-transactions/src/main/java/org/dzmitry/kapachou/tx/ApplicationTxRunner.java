package org.dzmitry.kapachou.tx;


import lombok.RequiredArgsConstructor;
import org.dzmitry.kapachou.tx.service.ProcessRequestService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
@RequiredArgsConstructor
public class ApplicationTxRunner implements ApplicationRunner {

    final ProcessRequestService prService;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ApplicationTxRunner.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        prService.distributedProcessRequestUpdate();
    }
}
