package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.port.PaymentRepositoryPort;
import com.pragma.payments.infrastructure.adapter.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID>, PaymentRepositoryPort {

    @Override
    default PaymentEntity savePayment(PaymentEntity payment) {
        return save(payment);
    }

    @Override
    default Optional<PaymentEntity> findByPaymentId(UUID paymentId) {
        return findById(paymentId);
    }

    @Override
    default List<PaymentEntity> findByCustomerId(String customerId) {
        return findAllByCustomerId(customerId);
    }

    @Override
    default List<PaymentEntity> findByMerchantId(String merchantId) {
        return findAllByMerchantId(merchantId);
    }

    @Override
    default List<PaymentEntity> findByStatus(String status) {
        return findAllByStatus(status);
    }

    @Override
    default List<PaymentEntity> findByReferenceNumber(String referenceNumber) {
        return findAllByReferenceNumber(referenceNumber);
    }

    @Override
    default List<PaymentEntity> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return findAllByCreatedAtBetween(startDate, endDate);
    }

    @Query("SELECT p FROM PaymentEntity p WHERE p.customerId = :customerId AND p.status = :status ORDER BY p.createdAt DESC")
    List<PaymentEntity> findByCustomerIdAndStatus(@Param("customerId") String customerId, 
                                                   @Param("status") String status);

    @Query("SELECT p FROM PaymentEntity p WHERE p.merchantId = :merchantId AND p.createdAt >= :since ORDER BY p.createdAt DESC")
    List<PaymentEntity> findRecentByMerchantId(@Param("merchantId") String merchantId, 
                                                @Param("since") LocalDateTime since);

    @Query("SELECT COUNT(p) FROM PaymentEntity p WHERE p.merchantId = :merchantId AND p.status = :status AND p.createdAt >= :since")
    Long countByMerchantIdAndStatusSince(@Param("merchantId") String merchantId, 
                                         @Param("status") String status, 
                                         @Param("since") LocalDateTime since);

    @Query("SELECT p FROM PaymentEntity p WHERE p.referenceNumber = :referenceNumber AND p.customerId = :customerId")
    Optional<PaymentEntity> findByReferenceNumberAndCustomerId(@Param("referenceNumber") String referenceNumber, 
                                                                @Param("customerId") String customerId);

    List<PaymentEntity> findAllByCustomerId(String customerId);

    List<PaymentEntity> findAllByMerchantId(String merchantId);

    List<PaymentEntity> findAllByStatus(String status);

    List<PaymentEntity> findAllByReferenceNumber(String referenceNumber);

    List<PaymentEntity> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    boolean existsByReferenceNumberAndCustomerId(String referenceNumber, String customerId);

    boolean existsByPaymentIdAndStatus(UUID paymentId, String status);

    void deleteByPaymentId(UUID paymentId);

    @Query("SELECT p FROM PaymentEntity p WHERE p.paymentId = :paymentId AND p.merchantId = :merchantId")
    Optional<PaymentEntity> findByPaymentIdAndMerchantId(@Param("paymentId") UUID paymentId, 
                                                          @Param("merchantId") String merchantId);

    @Query("SELECT SUM(p.amount) FROM PaymentEntity p WHERE p.merchantId = :merchantId AND p.status = :status AND p.createdAt >= :since")
    java.math.BigDecimal sumAmountByMerchantIdAndStatusSince(@Param("merchantId") String merchantId, 
                                                              @Param("status") String status, 
                                                              @Param("since") LocalDateTime since);

    @Query("SELECT p FROM PaymentEntity p WHERE p.customerId = :customerId ORDER BY p.createdAt DESC LIMIT :limit")
    List<PaymentEntity> findLatestByCustomerId(@Param("customerId") String customerId, 
                                               @Param("limit") int limit);

    @Query("SELECT p FROM PaymentEntity p WHERE p.status = :status AND p.updatedAt < :olderThan AND p.retryCount < :maxRetries")
    List<PaymentEntity> findPaymentsToRetry(@Param("status") String status, 
                                            @Param("olderThan") LocalDateTime olderThan, 
                                            @Param("maxRetries") int maxRetries);
}