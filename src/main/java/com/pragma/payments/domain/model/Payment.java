package com.pragma.payments.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidad de dominio que representa un pago en el sistema.
 * Contiene los datos esenciales de una transacción financiera y su estado.
 */
@Getter
@Builder(toBuilder = true)
@ToString
public class Payment {
    private final UUID paymentId;
    private final String referenceNumber;
    private final String customerId;
    private final String merchantId;
    private final BigDecimal amount;
    private final String currency;
    private final PaymentMethod paymentMethod;
    private final PaymentStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String fraudEngineResponse;
    private final String riskBureauResponse;
    private final String paymentGatewayResponse;

    /**
     * Crea una nueva instancia de Payment con los valores iniciales.
     * @param referenceNumber Número de referencia único de la transacción.
     * @param customerId Identificador del cliente.
     * @param merchantId Identificador del comercio.
     * @param amount Monto de la transacción.
     * @param currency Moneda de la transacción.
     * @param paymentMethod Método de pago utilizado.
     * @return Nueva instancia de Payment.
     */
    public static Payment create(String referenceNumber, String customerId, String merchantId,
                              BigDecimal amount, String currency, PaymentMethod paymentMethod) {
        LocalDateTime now = LocalDateTime.now();
        return Payment.builder()
                .paymentId(UUID.randomUUID())
                .referenceNumber(referenceNumber)
                .customerId(customerId)
                .merchantId(merchantId)
                .amount(amount)
                .currency(currency)
                .paymentMethod(paymentMethod)
                .status(PaymentStatus.PENDING)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    /**
     * Actualiza el estado del pago y registra la respuesta del motor de fraude.
     * @param status Nuevo estado del pago.
     * @param fraudEngineResponse Respuesta del motor de fraude.
     * @return Nueva instancia de Payment con los valores actualizados.
     */
    public Payment withFraudEngineResponse(PaymentStatus status, String fraudEngineResponse) {
        return this.toBuilder()
                .status(status)
                .fraudEngineResponse(fraudEngineResponse)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    /**
     * Actualiza el estado del pago y registra la respuesta del buró de riesgos.
     * @param status Nuevo estado del pago.
     * @param riskBureauResponse Respuesta del buró de riesgos.
     * @return Nueva instancia de Payment con los valores actualizados.
     */
    public Payment withRiskBureauResponse(PaymentStatus status, String riskBureauResponse) {
        return this.toBuilder()
                .status(status)
                .riskBureauResponse(riskBureauResponse)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    /**
     * Actualiza el estado del pago y registra la respuesta del gateway de pagos.
     * @param status Nuevo estado del pago.
     * @param paymentGatewayResponse Respuesta del gateway de pagos.
     * @return Nueva instancia de Payment con los valores actualizados.
     */
    public Payment withPaymentGatewayResponse(PaymentStatus status, String paymentGatewayResponse) {
        return this.toBuilder()
                .status(status)
                .paymentGatewayResponse(paymentGatewayResponse)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, BANK_TRANSFER, MOBILE_PAYMENT
    }

    public enum PaymentStatus {
        PENDING, APPROVED, REJECTED, FRAUD_DETECTED, RISK_REJECTED, PROCESSED
    }
}