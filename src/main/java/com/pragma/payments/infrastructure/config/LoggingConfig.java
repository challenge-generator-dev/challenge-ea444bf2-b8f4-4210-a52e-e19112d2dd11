package com.pragma.payments.infrastructure.config;

import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingConfiguration;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class LoggingConfig implements WebMvcConfigurer {

    private static final String LOG_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n";
    private static final String PAYMENT_LOGGER = "com.pragma.payments";
    private static final String CONTROLLER_LOGGER = "com.pragma.payments.infrastructure.controller";
    private static final String SERVICE_LOGGER = "com.pragma.payments.application";
    private static final String ADAPTER_LOGGER = "com.pragma.payments.infrastructure.adapter";

    @Bean
    public LoggingConfiguration loggingConfiguration(Environment environment) {
        LoggingConfiguration config = new LoggingConfiguration();
        
        Map<String, LogLevel> logLevels = new HashMap<>();
        logLevels.put(PAYMENT_LOGGER, LogLevel.INFO);
        logLevels.put(CONTROLLER_LOGGER, LogLevel.DEBUG);
        logLevels.put(SERVICE_LOGGER, LogLevel.DEBUG);
        logLevels.put(ADAPTER_LOGGER, LogLevel.DEBUG);
        
        logLevels.put("org.springframework.web", LogLevel.WARN);
        logLevels.put("org.springframework.boot", LogLevel.INFO);
        logLevels.put("org.hibernate", LogLevel.WARN);
        logLevels.put("org.postgresql", LogLevel.WARN);
        logLevels.put("io.github.resilience4j", LogLevel.INFO);
        
        return config;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLoggingInterceptor());
    }

    @Bean
    public RequestLoggingInterceptor requestLoggingInterceptor() {
        return new RequestLoggingInterceptor();
    }
}