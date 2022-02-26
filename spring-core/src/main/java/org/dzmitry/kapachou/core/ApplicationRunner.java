package org.dzmitry.kapachou.core;

import lombok.AllArgsConstructor;
import org.dzmitry.kapachou.core.circledependency.AuditingProcessor;
import org.dzmitry.kapachou.core.circledependency.NotificationProcessor;
import org.dzmitry.kapachou.core.scope.ColorService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@SpringBootApplication
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

    // #case:1
    private final ColorService colorService;
    // #case:2
    private final AuditingProcessor auditingProcessor;
    private final NotificationProcessor notificationProcessor;

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(ApplicationRunner.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        // case# 1
//        System.out.println("===prototype scope verification===");
//        IntStream.range(0, 10).mapToObj(i -> colorService.getColor())
//                .forEach(System.out::println);
//
//        // case# 2
//        System.out.println("auditingProcessor object is :" + auditingProcessor);
//        System.out.println("notificationProcessor object is :" + notificationProcessor);

    }
}
