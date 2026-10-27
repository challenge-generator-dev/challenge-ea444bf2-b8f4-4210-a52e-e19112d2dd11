package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

/**
 * Puerto que define la interfaz para interactuar con el motor de fraude.
 * La implementación concreta se proporciona en la capa de infraestructura.
 */
public interface FraudEnginePort {
    /**
     * Evalúa una transacción con el motor de fraude.
     * @param payment Pago a evaluar.
     * @return Mono que emite el pago actualizado con el resultado de la evaluación de fraude.
     * @throws com.pragma.payments.domain.exception.FraudEngineException si ocurre un error en la comunicación.
     */
    Mono<Payment> evaluateForFraud(Payment payment);
}