package com.pragma.payments.domain.exception;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentGatewayException extends RuntimeException {

    private final String gatewayName;
    private final String responseCode;
    private final UUID paymentId;
    private final BigDecimal amount;
    private final String merchantId;
    private final boolean retryable;

    public PaymentGatewayException(String message) {
        super(message);
        this.gatewayName = null;
        this.responseCode = null;
        this.paymentId = null;
        this.amount = null;
        this.merchantId = null;
        this.retryable = false;
    }

    public PaymentGatewayException(String message, String gatewayName, String responseCode) {
        super(message);
        this.gatewayName = gatewayName;
        this.responseCode = responseCode;
        this.paymentId = null;
        this.amount = null;
        this.merchantId = null;
        this.retryable = determineRetryability(responseCode);
    }

    public PaymentGatewayException(String message, String gatewayName, String responseCode, 
                                   UUID paymentId, BigDecimal amount, String merchantId) {
        super(message);
        this.gatewayName = gatewayName;
        this.responseCode = responseCode;
        this.paymentId = paymentId;
        this.amount = amount;
        this.merchantId = merchantId;
        this.retryable = determineRetryability(responseCode);
    }

    public PaymentGatewayException(String message, Throwable cause, String gatewayName, 
                                   String responseCode, UUID paymentId, BigDecimal amount, 
                                   String merchantId) {
        super(message, cause);
        this.gatewayName = gatewayName;
        this.responseCode = responseCode;
        this.paymentId = paymentId;
        this.amount = amount;
        this.merchantId = merchantId;
        this.retryable = determineRetryability(responseCode);
    }

    private boolean determineRetryability(String responseCode) {
        if (responseCode == null) {
            return false;
        }
        return responseCode.startsWith("TIMEOUT") || 
               responseCode.startsWith("NETWORK_ERROR") ||
               responseCode.equals("503") ||
               responseCode.equals("504");
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public boolean isRetryable() {
        return retryable;
    }

    @Override
    public String toString() {
        return "PaymentGatewayException{" +
                "message='" + getMessage() + '\'' +
                ", gatewayName='" + gatewayName + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", paymentId=" + paymentId +
                ", amount=" + amount +
                ", merchantId='" + merchantId + '\'' +
                ", retryable=" + retryable +
                '}';
    }
}