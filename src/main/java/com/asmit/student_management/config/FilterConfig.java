package com.asmit.student_management.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.asmit.student_management.security.JwtFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration(JwtFilter filter) {

        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);

        // 🔥 apply on all APIs
        registration.addUrlPatterns("/api/*");
 System.out.println("🔥 FILTER RUNNING");
        return registration;
       
    }
}