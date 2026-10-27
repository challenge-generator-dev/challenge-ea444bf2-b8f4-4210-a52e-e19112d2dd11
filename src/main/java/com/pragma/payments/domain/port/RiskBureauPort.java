package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

/**
 * Puerto que define la interfaz para interactuar con el buró de riesgos.
 * La implementación concreta se proporciona en la capa de infraestructura.
 */
public interface RiskBureauPort {
    /**
     * Evalúa el riesgo de una transacción con el buró de riesgos.
     * @param payment Pago a evaluar.
     * @return Mono que emite el pago actualizado con el resultado de la evaluación de riesgo.
     * @throws com.pragma.payments.domain.exception.RiskBureauException si ocurre un error en la comunicación.
     */
    Mono<Payment> evaluateRisk(Payment payment);
}