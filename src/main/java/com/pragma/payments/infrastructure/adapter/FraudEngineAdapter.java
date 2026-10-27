package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.FraudEnginePort;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class FraudEngineAdapter implements FraudEnginePort {

    private static final Logger log = LoggerFactory.getLogger(FraudEngineAdapter.class);

    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;

    public FraudEngineAdapter(
            RestTemplate restTemplate,
            @Qualifier("fraudEngineCircuitBreaker") CircuitBreaker circuitBreaker) {
        this.restTemplate = restTemplate;
        this.circuitBreaker = circuitBreaker;
    }

    @Override
    public Mono<Payment> evaluateForFraud(Payment payment) {
        log.info("Evaluando pago {} en motor de fraude", payment.getPaymentId());

        return Mono.fromCallable(() -> {
            String url = buildFraudEngineUrl(payment);
            log.debug("Llamando a motor de fraude: {}", url);

            Map<String, Object> requestBody = buildFraudRequest(payment);
            
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    url,
                    requestBody,
                    Map.class);

            return processFraudResponse(payment, response);
        })
        .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker, mono))
        .doOnSuccess(result -> log.info("Evaluacion de fraude exitosa para pago {}", 
                payment.getPaymentId()))
        .doOnError(error -> log.error("Error en evaluacion de fraude para pago {}: {}", 
                payment.getPaymentId(), error.getMessage()));
    }

    private String buildFraudEngineUrl(Payment payment) {
        return String.format("http://fraud-engine/api/v1/evaluate?paymentId=%s", 
                payment.getPaymentId());
    }

    private Map<String, Object> buildFraudRequest(Payment payment) {
        return Map.of(
                "paymentId", payment.getPaymentId().toString(),
                "referenceNumber", payment.getReferenceNumber(),
                "customerId", payment.getCustomerId(),
                "merchantId", payment.getMerchantId(),
                "amount", payment.getAmount().toString(),
                "currency", payment.getCurrency(),
                "paymentMethod", payment.getPaymentMethod().name()
        );
    }

    private Payment processFraudResponse(Payment payment, ResponseEntity<Map> response) {
        if (response.getBody() == null) {
            throw new FraudEngineException("Respuesta vacia del motor de fraude");
        }

        Map<String, Object> body = response.getBody();
        Boolean approved = (Boolean) body.get("approved");
        String riskScore = (String) body.get("riskScore");
        String recommendation = (String) body.getOrDefault("recommendation", "NONE");

        if (approved == null || !approved) {
            log.warn("Pago {} rechazado por motor de fraude. Score: {}, Recomendacion: {}",
                    payment.getPaymentId(), riskScore, recommendation);
            return payment.withFraudEngineResponse(PaymentStatus.REJECTED,
                    String.format("RiskScore: %s, Recommendation: %s", riskScore, recommendation));
        }

        log.debug("Pago {} aprobado por motor de fraude. Score: {}", 
                payment.getPaymentId(), riskScore);
        return payment.withFraudEngineResponse(PaymentStatus.PENDING,
                String.format("RiskScore: %s, Recommendation: %s", riskScore, recommendation));
    }

    private static class CircuitBreakerOperator<T> implements reactor.core.publisher.CoreOperator<T, T> {
        private final CircuitBreaker circuitBreaker;
        private final reactor.core.publisher.Mono<T> source;

        private CircuitBreakerOperator(CircuitBreaker circuitBreaker, 
                reactor.core.publisher.Mono<T> source) {
            this.circuitBreaker = circuitBreaker;
            this.source = source;
        }

        public static <T> CircuitBreakerOperator<T> of(CircuitBreaker circuitBreaker,
                reactor.core.publisher.Mono<T> source) {
            return new CircuitBreakerOperator<>(circuitBreaker, source);
        }

        @Override
        public reactor.core.publisher.CoreSubscriber<? super T> subscribe(
                reactor.core.publisher.CoreSubscriber<? super T> actual) {
            return source.subscribeWith(actual);
        }
    }
}