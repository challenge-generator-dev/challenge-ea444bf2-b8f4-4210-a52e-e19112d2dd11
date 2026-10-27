package com.pragma.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import java.time.Duration;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
public class Application {

    @Value("${fraud.engine.url}")
    private String fraudEngineUrl;

    @Value("${risk.bureau.url}")
    private String riskBureauUrl;

    @Value("${payment.gateway.url}")
    private String paymentGatewayUrl;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreaker fraudEngineCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .build();
        return circuitBreakerRegistry.circuitBreaker("fraudEngineCircuitBreaker", config);
    }

    @Bean
    public CircuitBreaker riskBureauCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .build();
        return circuitBreakerRegistry.circuitBreaker("riskBureauCircuitBreaker", config);
    }

    @Bean
    public CircuitBreaker paymentGatewayCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .build();
        return circuitBreakerRegistry.circuitBreaker("paymentGatewayCircuitBreaker", config);
    }

    @Bean
    public HealthIndicator fraudEngineHealthIndicator() {
        return () -> {
            try {
                // Simulación de verificación de salud del motor de fraude
                return Health.up().withDetail("url", fraudEngineUrl).build();
            } catch (Exception e) {
                return Health.down().withDetail("error", e.getMessage()).build();
            }
        };
    }

    @Bean
    public HealthIndicator riskBureauHealthIndicator() {
        return () -> {
            try {
                // Simulación de verificación de salud del buró de riesgos
                return Health.up().withDetail("url", riskBureauUrl).build();
            } catch (Exception e) {
                return Health.down().withDetail("error", e.getMessage()).build();
            }
        };
    }

    @Bean
    public HealthIndicator paymentGatewayHealthIndicator() {
        return () -> {
            try {
                // Simulación de verificación de salud del gateway de pagos
                return Health.up().withDetail("url", paymentGatewayUrl).build();
            } catch (Exception e) {
                return Health.down().withDetail("error", e.getMessage()).build();
            }
        };
    }
}