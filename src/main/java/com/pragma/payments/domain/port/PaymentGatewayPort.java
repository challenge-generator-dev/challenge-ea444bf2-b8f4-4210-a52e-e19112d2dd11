package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

/**
 * Puerto del dominio para la integración con el gateway de pagos.
 * Define el contrato que la infraestructura debe implementar para procesar pagos.
 */
public interface PaymentGatewayPort {

    /**
     * Procesa un pago a través del gateway externo.
     * @param payment la entidad de pago con los datos necesarios para la transacción
     * @return Mono que emite el pago actualizado con la respuesta del gateway
     */
    Mono<Payment> processPayment(Payment payment);

    /**
     * Consulta el estado de una transacción previamente procesada.
     * @param paymentId identificador único del pago
     * @return Mono que emite el pago con el estado actualizado
     */
    Mono<Payment> getPaymentStatus(String paymentId);

    /**
     * Cancela una transacción existente en el gateway.
     * @param paymentId identificador único del pago a cancelar
     * @return Mono que emite el pago con estado CANCELLED
     */
    Mono<Payment> cancelPayment(String paymentId);

    /**
     * Reembolsa un pago previamente procesado.
     * @param paymentId identificador único del pago a reembolsar
     * @param amount monto específico a reembolsar (puede ser parcial)
     * @return Mono que emite el pago con estado REFUNDED
     */
    Mono<Payment> refundPayment(String paymentId, java.math.BigDecimal amount);
}