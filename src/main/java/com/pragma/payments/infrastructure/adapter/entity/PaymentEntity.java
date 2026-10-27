package com.pragma.payments.infrastructure.adapter.entity;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentMethod;
import com.pragma.payments.domain.model.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments", indexes = {
        @Index(name = "idx_reference_number", columnList = "referenceNumber"),
        @Index(name = "idx_customer_id", columnList = "customerId"),
        @Index(name = "idx_merchant_id", columnList = "merchantId"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_created_at", columnList = "createdAt")
})
public class PaymentEntity {

    @Id
    @Column(name = "payment_id", nullable = false)
    private UUID paymentId;

    @Column(name = "reference_number", nullable = false, unique = true, length = 50)
    private String referenceNumber;

    @Column(name = "customer_id", nullable = false, length = 100)
    private String customerId;

    @Column(name = "merchant_id", nullable = false, length = 100)
    private String merchantId;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 20)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PaymentStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "fraud_engine_response", columnDefinition = "TEXT")
    private String fraudEngineResponse;

    @Column(name = "risk_bureau_response", columnDefinition = "TEXT")
    private String riskBureauResponse;

    @Column(name = "payment_gateway_response", columnDefinition = "TEXT")
    private String paymentGatewayResponse;

    public PaymentEntity() {
    }

    public static PaymentEntity fromDomain(Payment payment) {
        PaymentEntity entity = new PaymentEntity();
        entity.paymentId = payment.getPaymentId();
        entity.referenceNumber = payment.getReferenceNumber();
        entity.customerId = payment.getCustomerId();
        entity.merchantId = payment.getMerchantId();
        entity.amount = payment.getAmount();
        entity.currency = payment.getCurrency();
        entity.paymentMethod = payment.getPaymentMethod();
        entity.status = payment.getStatus();
        entity.createdAt = payment.getCreatedAt();
        entity.updatedAt = payment.getUpdatedAt();
        entity.fraudEngineResponse = payment.getFraudEngineResponse();
        entity.riskBureauResponse = payment.getRiskBureauResponse();
        entity.paymentGatewayResponse = payment.getPaymentGatewayResponse();
        return entity;
    }

    public Payment toDomain() {
        return Payment.builder()
                .paymentId(this.paymentId)
                .referenceNumber(this.referenceNumber)
                .customerId(this.customerId)
                .merchantId(this.merchantId)
                .amount(this.amount)
                .currency(this.currency)
                .paymentMethod(this.paymentMethod)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .fraudEngineResponse(this.fraudEngineResponse)
                .riskBureauResponse(this.riskBureauResponse)
                .paymentGatewayResponse(this.paymentGatewayResponse)
                .build();
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFraudEngineResponse() {
        return fraudEngineResponse;
    }

    public void setFraudEngineResponse(String fraudEngineResponse) {
        this.fraudEngineResponse = fraudEngineResponse;
    }

    public String getRiskBureauResponse() {
        return riskBureauResponse;
    }

    public void setRiskBureauResponse(String riskBureauResponse) {
        this.riskBureauResponse = riskBureauResponse;
    }

    public String getPaymentGatewayResponse() {
        return paymentGatewayResponse;
    }

    public void setPaymentGatewayResponse(String paymentGatewayResponse) {
        this.paymentGatewayResponse = paymentGatewayResponse;
    }
}