package com.pragma.payments.domain.exception;

/**
 * Excepción personalizada para errores provenientes del motor de fraude.
 * Se lanza cuando el servicio externo de evaluación de fraude falla o retorna
 * resultados que impiden continuar con el procesamiento del pago.
 */
public class FraudEngineException extends RuntimeException {

    private final String errorCode;
    private final String paymentId;
    private final boolean retryable;

    /**
     * Constructor con mensaje de error básico.
     * @param message descripción del error
     */
    public FraudEngineException(String message) {
        super(message);
        this.errorCode = "FRAUD_ENGINE_ERROR";
        this.paymentId = null;
        this.retryable = false;
    }

    /**
     * Constructor con mensaje y causa de la excepción.
     * @param message descripción del error
     * @param cause excepción original que causó el error
     */
    public FraudEngineException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "FRAUD_ENGINE_ERROR";
        this.paymentId = null;
        this.retryable = false;
    }

    /**
     * Constructor completo con todos los detalles del error.
     * @param message descripción del error
     * @param errorCode código específico del error del motor de fraude
     * @param paymentId identificador del pago que provocó el error
     * @param retryable indica si la operación puede reintentarse
     */
    public FraudEngineException(String message, String errorCode, String paymentId, boolean retryable) {
        super(message);
        this.errorCode = errorCode;
        this.paymentId = paymentId;
        this.retryable = retryable;
    }

    /**
     * Constructor completo con causa de la excepción.
     * @param message descripción del error
     * @param errorCode código específico del error
     * @param paymentId identificador del pago
     * @param retryable indica si la operación puede reintentarse
     * @param cause excepción original
     */
    public FraudEngineException(String message, String errorCode, String paymentId, boolean retryable, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.paymentId = paymentId;
        this.retryable = retryable;
    }

    /**
     * Crea una excepción para el caso de motor de fraude no disponible.
     * @param paymentId identificador del pago
     * @return excepción configurada como reintentable
     */
    public static FraudEngineException serviceUnavailable(String paymentId) {
        return new FraudEngineException(
            "El motor de fraude no está disponible en este momento",
            "FRAUD_SERVICE_UNAVAILABLE",
            paymentId,
            true
        );
    }

    /**
     * Crea una excepción para respuestas inválidas del motor de fraude.
     * @param paymentId identificador del pago
     * @param reason razón de la invalidez
     * @return excepción configurada como no reintentable
     */
    public static FraudEngineException invalidResponse(String paymentId, String reason) {
        return new FraudEngineException(
            "Respuesta inválida del motor de fraude: " + reason,
            "FRAUD_INVALID_RESPONSE",
            paymentId,
            false
        );
    }

    /**
     * Crea una excepción para pagos marcados como fraudulentos.
     * @param paymentId identificador del pago
     * @param details detalles adicionales del fraude detectado
     * @return excepción no reintentable ya que el pago fue rechazado
     */
    public static FraudEngineException fraudDetected(String paymentId, String details) {
        return new FraudEngineException(
            "Pago rechazado por sospecha de fraude: " + details,
            "FRAUD_DETECTED",
            paymentId,
            false
        );
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public boolean isRetryable() {
        return retryable;
    }

    @Override
    public String toString() {
        return "FraudEngineException{" +
                "errorCode='" + errorCode + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", retryable=" + retryable +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}