package com.pragma.payments.infrastructure.controller.dto;

import com.pragma.payments.domain.model.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponse(
        UUID paymentId,
        String referenceNumber,
        String customerId,
        String merchantId,
        BigDecimal amount,
        String currency,
        String paymentMethod,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String fraudEngineResponse,
        String riskBureauResponse,
        String paymentGatewayResponse
) {
    public static PaymentResponse fromDomain(Payment payment) {
        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getReferenceNumber(),
                payment.getCustomerId(),
                payment.getMerchantId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getPaymentMethod().name(),
                payment.getStatus().name(),
                payment.getCreatedAt(),
                payment.getUpdatedAt(),
                payment.getFraudEngineResponse(),
                payment.getRiskBureauResponse(),
                payment.getPaymentGatewayResponse()
        );
    }
}