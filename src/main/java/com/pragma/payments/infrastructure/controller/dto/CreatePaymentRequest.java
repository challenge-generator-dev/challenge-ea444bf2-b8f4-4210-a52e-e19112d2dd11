package com.pragma.payments.infrastructure.controller.dto;


import com.pragma.payments.domain.model.PaymentMethod;
import com.pragma.payments.domain.model.Payment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreatePaymentRequest(
        @NotBlank(message = "Reference number is required")
        @Size(min = 1, max = 100, message = "Reference number must be between 1 and 100 characters")
        String referenceNumber,
        
        @NotBlank(message = "Customer ID is required")
        @Size(min = 1, max = 50, message = "Customer ID must be between 1 and 50 characters")
        String customerId,
        
        @NotBlank(message = "Merchant ID is required")
        @Size(min = 1, max = 50, message = "Merchant ID must be between 1 and 50 characters")
        String merchantId,
        
        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,
        
        @NotBlank(message = "Currency is required")
        @Size(min = 3, max = 3, message = "Currency must be a 3-letter code")
        String currency,
        
        @NotBlank(message = "Payment method is required")
        String paymentMethod
) {
    public Payment.PaymentMethod getPaymentMethodAsEnum() {
        try {
            return Payment.PaymentMethod.valueOf(paymentMethod.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Payment.PaymentMethod.UNKNOWN;
        }
    }
}