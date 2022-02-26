package org.dzmitry.kapachou.mvc;

import org.dzmitry.kapachou.mvc.filter.PermissionFilter;
import org.dzmitry.kapachou.mvc.interceptor.LoggedEmployeeInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

@EnableWebMvc
@Configuration
public class WebConfiguration {

    @Value("${filters.permissionFilter.enabled}")
    private String enablePermissionFilter;

//    @Bean
//    public FilterRegistrationBean<PermissionFilter> permissionFilterRegistrationBean() {
//        System.out.printf("PermissionFilter enabling is %s", enablePermissionFilter);
//
//        FilterRegistrationBean<PermissionFilter> registrationBean = new FilterRegistrationBean<>();
//
//        registrationBean.setName("permissionFilter");
//        registrationBean.setFilter(new PermissionFilter());
//        registrationBean.setOrder(0);
//        registrationBean.setAsyncSupported(true);
//        registrationBean.addUrlPatterns("/*");
//
//        registrationBean.setEnabled(Boolean.parseBoolean(enablePermissionFilter));
//
//        return registrationBean;
//    }

//    @Bean
//    public InterceptorRegistration interceptorRegistration() {
//        return new InterceptorRegistration(new LoggedEmployeeInterceptor());
//    }


}
