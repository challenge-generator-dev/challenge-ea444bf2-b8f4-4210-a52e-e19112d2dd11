package com.pragma.payments.application;

import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.exception.PaymentGatewayException;
import com.pragma.payments.domain.exception.RiskBureauException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentMethod;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.FraudEnginePort;
import com.pragma.payments.domain.port.PaymentGatewayPort;
import com.pragma.payments.domain.port.PaymentRepositoryPort;
import com.pragma.payments.domain.port.RiskBureauPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final FraudEnginePort fraudEnginePort;
    private final RiskBureauPort riskBureauPort;
    private final PaymentGatewayPort paymentGatewayPort;
    private final PaymentRepositoryPort paymentRepositoryPort;

    public PaymentService(
            FraudEnginePort fraudEnginePort,
            RiskBureauPort riskBureauPort,
            PaymentGatewayPort paymentGatewayPort,
            PaymentRepositoryPort paymentRepositoryPort) {
        this.fraudEnginePort = fraudEnginePort;
        this.riskBureauPort = riskBureauPort;
        this.paymentGatewayPort = paymentGatewayPort;
        this.paymentRepositoryPort = paymentRepositoryPort;
    }

    public Mono<Payment> createPayment(
            String referenceNumber,
            String customerId,
            String merchantId,
            java.math.BigDecimal amount,
            String currency,
            PaymentMethod paymentMethod) {

        log.info("Iniciando proceso de pago para referencia: {}, cliente: {}, monto: {}",
                referenceNumber, customerId, amount);

        Payment payment = Payment.create(
                referenceNumber,
                customerId,
                merchantId,
                amount,
                currency,
                paymentMethod);

        return fraudEnginePort.evaluateForFraud(payment)
                .onErrorResume(FraudEngineException.class, ex -> {
                    log.error("Error en motor de fraude: {}", ex.getMessage());
                    Payment failedPayment = payment.withFraudEngineResponse(
                            PaymentStatus.FAILED,
                            "ERROR: " + ex.getMessage());
                    return paymentRepositoryPort.save(failedPayment)
                            .then(Mono.error(ex));
                })
                .flatMap(paymentWithFraud -> {
                    log.info("Evaluacion de fraude completada, estado: {}", 
                            paymentWithFraud.getStatus());
                    if (paymentWithFraud.getStatus() == PaymentStatus.REJECTED) {
                        log.warn("Pago rechazado por motor de fraude: {}", referenceNumber);
                        return paymentRepositoryPort.save(paymentWithFraud)
                                .then(Mono.just(paymentWithFraud));
                    }
                    return Mono.just(paymentWithFraud);
                })
                .flatMap(paymentAfterFraud -> 
                    riskBureauPort.evaluateRisk(paymentAfterFraud)
                        .onErrorResume(RiskBureauException.class, ex -> {
                            log.error("Error en bureau de riesgos: {}", ex.getMessage());
                            Payment failedPayment = paymentAfterFraud.withRiskBureauResponse(
                                    PaymentStatus.FAILED,
                                    "ERROR: " + ex.getMessage());
                            return paymentRepositoryPort.save(failedPayment)
                                    .then(Mono.error(ex));
                        })
                )
                .flatMap(paymentWithRisk -> {
                    log.info("Evaluacion de riesgo completada, estado: {}", 
                            paymentWithRisk.getStatus());
                    if (paymentWithRisk.getStatus() == PaymentStatus.REJECTED) {
                        log.warn("Pago rechazado por bureau de riesgos: {}", referenceNumber);
                        return paymentRepositoryPort.save(paymentWithRisk)
                                .then(Mono.just(paymentWithRisk));
                    }
                    return Mono.just(paymentWithRisk);
                })
                .flatMap(paymentAfterRisk ->
                    paymentGatewayPort.processPayment(paymentAfterRisk)
                        .onErrorResume(PaymentGatewayException.class, ex -> {
                            log.error("Error en gateway de pagos: {}", ex.getMessage());
                            Payment failedPayment = paymentAfterRisk.withPaymentGatewayResponse(
                                    PaymentStatus.FAILED,
                                    "ERROR: " + ex.getMessage());
                            return paymentRepositoryPort.save(failedPayment)
                                    .then(Mono.error(ex));
                        })
                )
                .flatMap(paymentFinal -> {
                    log.info("Procesamiento de pago completado, estado final: {}", 
                            paymentFinal.getStatus());
                    return paymentRepositoryPort.save(paymentFinal)
                            .then(Mono.just(paymentFinal));
                });
    }

    public Mono<Payment> getPaymentById(java.util.UUID paymentId) {
        log.info("Consultando pago con ID: {}", paymentId);
        return paymentRepositoryPort.findById(paymentId)
                .doOnSuccess(payment -> {
                    if (payment != null) {
                        log.debug("Pago encontrado: {}", payment.getReferenceNumber());
                    } else {
                        log.warn("Pago no encontrado para ID: {}", paymentId);
                    }
                });
    }

    public Mono<java.util.List<Payment>> getPaymentsByCustomer(String customerId) {
        log.info("Consultando pagos para cliente: {}", customerId);
        return paymentRepositoryPort.findByCustomerId(customerId)
                .doOnSuccess(payments -> 
                    log.info("Se encontraron {} pagos para cliente: {}", 
                            payments.size(), customerId));
    }

    public Mono<java.util.List<Payment>> getPaymentsByMerchant(String merchantId) {
        log.info("Consultando pagos para comercio: {}", merchantId);
        return paymentRepositoryPort.findByMerchantId(merchantId)
                .doOnSuccess(payments -> 
                    log.info("Se encontraron {} pagos para comercio: {}", 
                            payments.size(), merchantId));
    }
}