package com.pragma.payments.infrastructure.config;


import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.exception.PaymentGatewayException;
import com.pragma.payments.domain.exception.RiskBureauException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);
    private static final String TIMESTAMP_FIELD = "timestamp";
    private static final String STATUS_FIELD = "status";
    private static final String ERROR_FIELD = "error";
    private static final String MESSAGE_FIELD = "message";
    private static final String DETAILS_FIELD = "details";
    private static final String PATH_FIELD = "path";

    @ExceptionHandler(FraudEngineException.class)
    public ResponseEntity<Map<String, Object>> handleFraudEngineException(
            FraudEngineException ex, WebRequest request) {
        log.error("Error del motor de fraude: {}", ex.getMessage(), ex);
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Fraud Engine Error",
                ex.getMessage(),
                request
        );
        
        body.put("service", "fraud-engine");
        body.put("originalStatus", ex.getStatus());
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @ExceptionHandler(RiskBureauException.class)
    public ResponseEntity<Map<String, Object>> handleRiskBureauException(
            RiskBureauException ex, WebRequest request) {
        log.error("Error del bureau de riesgos: {}", ex.getMessage(), ex);
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Risk Bureau Error",
                ex.getMessage(),
                request
        );
        
        body.put("service", "risk-bureau");
        body.put("originalStatus", ex.getStatus());
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @ExceptionHandler(PaymentGatewayException.class)
    public ResponseEntity<Map<String, Object>> handlePaymentGatewayException(
            PaymentGatewayException ex, WebRequest request) {
        log.error("Error del gateway de pagos: {}", ex.getMessage(), ex);
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Payment Gateway Error",
                ex.getMessage(),
                request
        );
        
        body.put("service", "payment-gateway");
        body.put("originalStatus", ex.getStatus());
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {
        log.warn("Error de validación: {}", ex.getMessage());
        
        Map<String, String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage() != null 
                                ? error.getDefaultMessage() 
                                : "Valor inválido",
                        (existing, replacement) -> existing
                ));
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Los datos proporcionados no son válidos",
                request
        );
        
        body.put(DETAILS_FIELD, validationErrors);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        log.warn("Argumento ilegal: {}", ex.getMessage());
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(
            Exception ex, WebRequest request) {
        log.error("Error inesperado del sistema: {}", ex.getMessage(), ex);
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Ha ocurrido un error inesperado. Por favor, contacte al administrador.",
                request
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private Map<String, Object> buildErrorResponse(int status, String error, 
                                                    String message, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP_FIELD, LocalDateTime.now().toString());
        body.put(STATUS_FIELD, status);
        body.put(ERROR_FIELD, error);
        body.put(MESSAGE_FIELD, message);
        body.put(PATH_FIELD, extractPath(request));
        return body;
    }

    private String extractPath(WebRequest request) {
        String description = request.getDescription(false);
        if (description.startsWith("uri=")) {
            return description.substring(4);
        }
        return description;
    }
}