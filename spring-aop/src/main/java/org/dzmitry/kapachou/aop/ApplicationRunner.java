package org.dzmitry.kapachou.aop;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class ApplicationRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(ApplicationRunner.class).bannerMode(Banner.Mode.OFF).run(args);
    }

}
