package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Puerto del dominio para operaciones de persistencia de pagos.
 * Define el contrato para guardar, actualizar y consultar pagos en la capa de datos.
 */
public interface PaymentRepositoryPort {

    /**
     * Persiste un nuevo pago en el sistema.
     * @param payment la entidad de pago a guardar
     * @return Mono que emite el pago persistido con su ID asignado
     */
    Mono<Payment> save(Payment payment);

    /**
     * Actualiza el estado de un pago existente.
     * @param paymentId identificador único del pago
     * @param status nuevo estado del pago
     * @param response respuesta del servicio externo que actualizó el estado
     * @return Mono que emite el pago actualizado
     */
    Mono<Payment> updateStatus(UUID paymentId, PaymentStatus status, String response);

    /**
     * Busca un pago por su identificador único.
     * @param paymentId identificador del pago
     * @return Mono que emite el pago encontrado o vacío si no existe
     */
    Mono<Payment> findById(UUID paymentId);

    /**
     * Busca un pago por su número de referencia único.
     * @param referenceNumber número de referencia del pago
     * @return Mono que emite el pago encontrado o vacío
     */
    Mono<Payment> findByReferenceNumber(String referenceNumber);

    /**
     * Recupera todos los pagos de un cliente específico.
     * @param customerId identificador del cliente
     * @return Lista de pagos del cliente
     */
    Mono<List<Payment>> findByCustomerId(String customerId);

    /**
     * Recupera todos los pagos de un comerciante específico.
     * @param merchantId identificador del comerciante
     * @return Lista de pagos del comerciante
     */
    Mono<List<Payment>> findByMerchantId(String merchantId);

    /**
     * Busca pagos por estado específico.
     * @param status estado de los pagos a buscar
     * @return Lista de pagos con el estado especificado
     */
    Mono<List<Payment>> findByStatus(PaymentStatus status);

    /**
     * Recupera pagos dentro de un rango de fechas.
     * @param startDate fecha de inicio del rango
     * @param endDate fecha de fin del rango
     * @return Lista de pagos creados en el período especificado
     */
    Mono<List<Payment>> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Obtiene el total de pagos realizados por un cliente.
     * @param customerId identificador del cliente
     * @return suma total de los montos de los pagos
     */
    Mono<BigDecimal> getTotalAmountByCustomerId(String customerId);

    /**
     * Elimina un pago del sistema.
     * @param paymentId identificador del pago a eliminar
     * @return Mono que completa cuando la eliminación es exitosa
     */
    Mono<Void> deleteById(UUID paymentId);
}