package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.exception.PaymentGatewayException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.port.PaymentGatewayPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PaymentGatewayAdapter implements PaymentGatewayPort {

    private static final Logger log = LoggerFactory.getLogger(PaymentGatewayAdapter.class);
    private final RestTemplate restTemplate;
    private final String paymentGatewayUrl;
    private static final String CIRCUIT_BREAKER_NAME = "paymentGateway";

    public PaymentGatewayAdapter(RestTemplate restTemplate,
                                  @Value("${external.services.payment-gateway.url}") String paymentGatewayUrl) {
        this.restTemplate = restTemplate;
        this.paymentGatewayUrl = paymentGatewayUrl;
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "processPaymentFallback")
    public Mono<Payment> processPayment(Payment payment) {
        return Mono.fromCallable(() -> {
            log.info("Procesando pago en gateway para referencia: {}", payment.getReferenceNumber());
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("paymentId", payment.getPaymentId().toString());
            requestBody.put("referenceNumber", payment.getReferenceNumber());
            requestBody.put("customerId", payment.getCustomerId());
            requestBody.put("merchantId", payment.getMerchantId());
            requestBody.put("amount", payment.getAmount().toPlainString());
            requestBody.put("currency", payment.getCurrency());
            requestBody.put("paymentMethod", payment.getPaymentMethod().name());
            
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
            
            Map<String, Object> response = restTemplate.postForObject(
                    paymentGatewayUrl + "/api/v1/payments/process",
                    request,
                    Map.class
            );
            
            if (response == null) {
                throw new PaymentGatewayException("Respuesta nula del gateway de pagos");
            }
            
            String statusStr = (String) response.get("status");
            PaymentStatus gatewayStatus = mapGatewayStatus(statusStr);
            String gatewayResponse = buildGatewayResponse(response);
            
            log.info("Pago procesado exitosamente. Estado: {}", gatewayStatus);
            
            return payment.withPaymentGatewayResponse(gatewayStatus, gatewayResponse);
        });
    }

    private PaymentStatus mapGatewayStatus(String gatewayStatus) {
        if (gatewayStatus == null) {
            return PaymentStatus.FAILED;
        }
        
        return switch (gatewayStatus.toUpperCase()) {
            case "APPROVED", "AUTHORIZED", "SUCCESS" -> PaymentStatus.APPROVED;
            case "PENDING" -> PaymentStatus.PENDING;
            case "DECLINED", "REJECTED" -> PaymentStatus.DECLINED;
            case "CANCELLED" -> PaymentStatus.CANCELLED;
            default -> PaymentStatus.FAILED;
        };
    }

    private String buildGatewayResponse(Map<String, Object> response) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"transactionId\":\"").append(response.get("transactionId")).append("\",");
        sb.append("\"status\":\"").append(response.get("status")).append("\",");
        sb.append("\"timestamp\":\"").append(LocalDateTime.now()).append("\"");
        
        if (response.containsKey("authorizationCode")) {
            sb.append(",\"authorizationCode\":\"").append(response.get("authorizationCode")).append("\"");
        }
        if (response.containsKey("message")) {
            sb.append(",\"message\":\"").append(response.get("message")).append("\"");
        }
        
        sb.append("}");
        return sb.toString();
    }

    private Mono<Payment> processPaymentFallback(Payment payment, Throwable t) {
        log.error("Fallback activado para PaymentGateway. Referencia: {}, Error: {}", 
                payment.getReferenceNumber(), t.getMessage());
        
        String fallbackResponse = "{\"status\":\"FALLBACK\",\"error\":\"" + t.getMessage() + "\"}";
        return Mono.just(payment.withPaymentGatewayResponse(PaymentStatus.FAILED, fallbackResponse));
    }
}