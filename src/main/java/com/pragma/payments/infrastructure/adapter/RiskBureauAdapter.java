package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.exception.RiskBureauException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.RiskBureauPort;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class RiskBureauAdapter implements RiskBureauPort {

    private static final Logger log = LoggerFactory.getLogger(RiskBureauAdapter.class);

    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;

    public RiskBureauAdapter(
            RestTemplate restTemplate,
            @Qualifier("riskBureauCircuitBreaker") CircuitBreaker circuitBreaker) {
        this.restTemplate = restTemplate;
        this.circuitBreaker = circuitBreaker;
    }

    @Override
    public Mono<Payment> evaluateRisk(Payment payment) {
        log.info("Evaluando riesgo para pago {} en bureau", payment.getPaymentId());

        return Mono.fromCallable(() -> {
            String url = buildRiskBureauUrl(payment);
            log.debug("Llamando a bureau de riesgos: {}", url);

            Map<String, Object> requestBody = buildRiskRequest(payment);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    url,
                    requestBody,
                    Map.class);

            return processRiskResponse(payment, response);
        })
        .transformDeferred(mono -> RiskBureauCircuitBreakerOperator.of(circuitBreaker, mono))
        .doOnSuccess(result -> log.info("Evaluacion de riesgo exitosa para pago {}", 
                payment.getPaymentId()))
        .doOnError(error -> log.error("Error en evaluacion de riesgo para pago {}: {}", 
                payment.getPaymentId(), error.getMessage()));
    }

    private String buildRiskBureauUrl(Payment payment) {
        return String.format("http://risk-bureau/api/v2/assess?paymentId=%s", 
                payment.getPaymentId());
    }

    private Map<String, Object> buildRiskRequest(Payment payment) {
        Map<String, Object> request = new HashMap<>();
        request.put("paymentId", payment.getPaymentId().toString());
        request.put("customerId", payment.getCustomerId());
        request.put("merchantId", payment.getMerchantId());
        request.put("amount", payment.getAmount().toString());
        request.put("currency", payment.getCurrency());
        request.put("paymentMethod", payment.getPaymentMethod().name());
        request.put("fraudEngineResponse", payment.getFraudEngineResponse());
        return request;
    }

    private Payment processRiskResponse(Payment payment, ResponseEntity<Map> response) {
        if (response.getBody() == null) {
            throw new RiskBureauException("Respuesta vacia del bureau de riesgos");
        }

        Map<String, Object> body = response.getBody();
        String riskCategory = (String) body.get("riskCategory");
        Integer riskLevel = (Integer) body.get("riskLevel");
        String creditScore = (String) body.get("creditScore");

        if (riskLevel == null || riskLevel > 70) {
            log.warn("Pago {} rechazado por bureau de riesgos. Categoria: {}, Nivel: {}",
                    payment.getPaymentId(), riskCategory, riskLevel);
            return payment.withRiskBureauResponse(PaymentStatus.REJECTED,
                    String.format("Category: %s, Level: %d, CreditScore: %s", 
                            riskCategory, riskLevel, creditScore));
        }

        log.debug("Pago {} aprobado por bureau de riesgos. Categoria: {}, Nivel: {}", 
                payment.getPaymentId(), riskCategory, riskLevel);
        return payment.withRiskBureauResponse(PaymentStatus.PENDING,
                String.format("Category: %s, Level: %d, CreditScore: %s", 
                        riskCategory, riskLevel, creditScore));
    }

    private static class RiskBureauCircuitBreakerOperator<T> 
            implements reactor.core.publisher.CoreOperator<T, T> {
        private final CircuitBreaker circuitBreaker;
        private final reactor.core.publisher.Mono<T> source;

        private RiskBureauCircuitBreakerOperator(CircuitBreaker circuitBreaker, 
                reactor.core.publisher.Mono<T> source) {
            this.circuitBreaker = circuitBreaker;
            this.source = source;
        }

        public static <T> RiskBureauCircuitBreakerOperator<T> of(CircuitBreaker circuitBreaker,
                reactor.core.publisher.Mono<T> source) {
            return new RiskBureauCircuitBreakerOperator<>(circuitBreaker, source);
        }

        @Override
        public reactor.core.publisher.CoreSubscriber<? super T> subscribe(
                reactor.core.publisher.CoreSubscriber<? super T> actual) {
            return source.subscribeWith(actual);
        }
    }
}