package com.pragma.payments.domain.exception;

import java.util.UUID;

public class RiskBureauException extends RuntimeException {

    private final String bureauIdentifier;
    private final String errorCode;
    private final UUID paymentId;
    private final String customerId;

    public RiskBureauException(String message) {
        super(message);
        this.bureauIdentifier = null;
        this.errorCode = null;
        this.paymentId = null;
        this.customerId = null;
    }

    public RiskBureauException(String message, String bureauIdentifier, String errorCode) {
        super(message);
        this.bureauIdentifier = bureauIdentifier;
        this.errorCode = errorCode;
        this.paymentId = null;
        this.customerId = null;
    }

    public RiskBureauException(String message, String bureauIdentifier, String errorCode, 
                               UUID paymentId, String customerId) {
        super(message);
        this.bureauIdentifier = bureauIdentifier;
        this.errorCode = errorCode;
        this.paymentId = paymentId;
        this.customerId = customerId;
    }

    public RiskBureauException(String message, Throwable cause, String bureauIdentifier, 
                               String errorCode, UUID paymentId, String customerId) {
        super(message, cause);
        this.bureauIdentifier = bureauIdentifier;
        this.errorCode = errorCode;
        this.paymentId = paymentId;
        this.customerId = customerId;
    }

    public String getBureauIdentifier() {
        return bureauIdentifier;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "RiskBureauException{" +
                "message='" + getMessage() + '\'' +
                ", bureauIdentifier='" + bureauIdentifier + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", paymentId=" + paymentId +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}