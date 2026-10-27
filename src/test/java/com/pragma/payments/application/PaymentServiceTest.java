package com.pragma.payments.application;

import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.exception.PaymentGatewayException;
import com.pragma.payments.domain.exception.RiskBureauException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentMethod;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.FraudEnginePort;
import com.pragma.payments.domain.port.PaymentGatewayPort;
import com.pragma.payments.domain.port.PaymentRepositoryPort;
import com.pragma.payments.domain.port.RiskBureauPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentService - Pruebas Unitarias")
class PaymentServiceTest {

    @Mock
    private FraudEnginePort fraudEnginePort;

    @Mock
    private RiskBureauPort riskBureauPort;

    @Mock
    private PaymentGatewayPort paymentGatewayPort;

    @Mock
    private PaymentRepositoryPort paymentRepositoryPort;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(
            fraudEnginePort,
            riskBureauPort,
            paymentGatewayPort,
            paymentRepositoryPort
        );
    }

    private Payment createTestPayment() {
        return Payment.create(
            "REF-001",
            "customer-123",
            "merchant-456",
            new BigDecimal("1000.00"),
            "USD",
            PaymentMethod.CREDIT_CARD
        );
    }

    @Nested
    @DisplayName("Escenario: Procesamiento exitoso de pago")
    class ProcessPaymentSuccess {

        @Test
        @DisplayName("Debe procesar pago exitosamente cuando todos los servicios externos responden correctamente")
        void shouldProcessPaymentSuccessfully() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.APPROVED, "risk-low"
            );
            Payment finalPayment = paymentWithRisk.withPaymentGatewayResponse(
                PaymentStatus.COMPLETED, "payment-authorized"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getStatus()).isEqualTo(PaymentStatus.COMPLETED);
                    assertThat(result.getFraudEngineResponse()).isEqualTo("fraud-check-passed");
                    assertThat(result.getRiskBureauResponse()).isEqualTo("risk-low");
                    assertThat(result.getPaymentGatewayResponse()).isEqualTo("payment-authorized");
                })
                .verifyComplete();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort).evaluateRisk(paymentWithFraud);
            verify(paymentGatewayPort).process(paymentWithRisk);
            verify(paymentRepositoryPort).save(finalPayment);
        }

        @Test
        @DisplayName("Debe rechazar pago cuando el motor de fraude lo marca como fraudulento")
        void shouldRejectPaymentWhenFraudDetected() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.REJECTED, "fraud-detected-high-risk"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getStatus()).isEqualTo(PaymentStatus.REJECTED);
                    assertThat(result.getFraudEngineResponse()).contains("fraud-detected");
                })
                .verifyComplete();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort, never()).evaluateRisk(any(Payment.class));
            verify(paymentGatewayPort, never()).process(any(Payment.class));
        }

        @Test
        @DisplayName("Debe rechazar pago cuando el buró de riesgos indica alto riesgo")
        void shouldRejectPaymentWhenHighRisk() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.REJECTED, "risk-score-85-high-risk"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getStatus()).isEqualTo(PaymentStatus.REJECTED);
                    assertThat(result.getRiskBureauResponse()).contains("high-risk");
                })
                .verifyComplete();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort).evaluateRisk(paymentWithFraud);
            verify(paymentGatewayPort, never()).process(any(Payment.class));
        }

        @Test
        @DisplayName("Debe fallar cuando el gateway de pagos rechaza la transacción")
        void shouldFailWhenGatewayRejects() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.APPROVED, "risk-low"
            );
            Payment paymentWithGateway = paymentWithRisk.withPaymentGatewayResponse(
                PaymentStatus.FAILED, "insufficient-funds"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithGateway));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithGateway));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getStatus()).isEqualTo(PaymentStatus.FAILED);
                    assertThat(result.getPaymentGatewayResponse()).contains("insufficient-funds");
                })
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenario: Manejo de errores de servicios externos")
    class ExternalServiceErrorHandling {

        @Test
        @DisplayName("Debe manejar excepción del motor de fraude y propagar error")
        void shouldHandleFraudEngineException() {
            Payment payment = createTestPayment();

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.error(new FraudEngineException("Servicio de fraude no disponible")));

            StepVerifier.create(paymentService.processPayment(payment))
                .expectError(FraudEngineException.class)
                .verify();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort, never()).evaluateRisk(any(Payment.class));
            verify(paymentGatewayPort, never()).process(any(Payment.class));
            verify(paymentRepositoryPort, never()).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe manejar excepción del buró de riesgos y propagar error")
        void shouldHandleRiskBureauException() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.error(new RiskBureauException("Conexión con buró de riesgos fallida")));

            StepVerifier.create(paymentService.processPayment(payment))
                .expectError(RiskBureauException.class)
                .verify();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort).evaluateRisk(paymentWithFraud);
            verify(paymentGatewayPort, never()).process(any(Payment.class));
            verify(paymentRepositoryPort, never()).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe manejar excepción del gateway de pagos y propagar error")
        void shouldHandlePaymentGatewayException() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.APPROVED, "risk-low"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.error(new PaymentGatewayException("Gateway de pagos fuera de servicio")));

            StepVerifier.create(paymentService.processPayment(payment))
                .expectError(PaymentGatewayException.class)
                .verify();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort).evaluateRisk(paymentWithFraud);
            verify(paymentGatewayPort).process(paymentWithRisk);
            verify(paymentRepositoryPort, never()).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe manejar error al persistir pago en repositorio")
        void shouldHandleRepositorySaveError() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.APPROVED, "risk-low"
            );
            Payment finalPayment = paymentWithRisk.withPaymentGatewayResponse(
                PaymentStatus.COMPLETED, "payment-authorized"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error de conexión a base de datos")));

            StepVerifier.create(paymentService.processPayment(payment))
                .expectError(RuntimeException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenario: Validación de parámetros de entrada")
    class InputValidation {

        @Test
        @DisplayName("Debe rechazar pago nulo con excepción")
        void shouldRejectNullPayment() {
            StepVerifier.create(paymentService.processPayment(null))
                .expectError(NullPointerException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenario: Verificación de flujo de datos")
    class DataFlowVerification {

        @Test
        @DisplayName("Debe pasar el paymentId generado a través de todo el pipeline")
        void shouldPreservePaymentIdThroughPipeline() {
            Payment payment = createTestPayment();
            UUID paymentId = payment.getPaymentId();

            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "ok"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.APPROVED, "ok"
            );
            Payment finalPayment = paymentWithRisk.withPaymentGatewayResponse(
                PaymentStatus.COMPLETED, "ok"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));

            ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> assertThat(result.getPaymentId()).isEqualTo(paymentId))
                .verifyComplete();

            verify(paymentRepositoryPort).save(paymentCaptor.capture());
            assertThat(paymentCaptor.getValue().getPaymentId()).isEqualTo(paymentId);
        }

        @Test
        @DisplayName("Debe mantener el monto y moneda originales durante todo el procesamiento")
        void shouldPreserveAmountAndCurrency() {
            BigDecimal originalAmount = new BigDecimal("5000.00");
            String originalCurrency = "EUR";

            Payment payment = Payment.create(
                "REF-TEST",
                "customer-789",
                "merchant-101",
                originalAmount,
                originalCurrency,
                PaymentMethod.DEBIT_CARD
            );

            Payment paymentWithFraud = payment.withFraudEngineResponse(PaymentStatus.APPROVED, "ok");
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(PaymentStatus.APPROVED, "ok");
            Payment finalPayment = paymentWithRisk.withPaymentGatewayResponse(PaymentStatus.COMPLETED, "ok");

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getAmount()).isEqualByComparingTo(originalAmount);
                    assertThat(result.getCurrency()).isEqualTo(originalCurrency);
                })
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenario: Trazabilidad del pago")
    class PaymentTraceability {

        @Test
        @DisplayName("Debe registrar timestamps de creación y actualización")
        void shouldRecordTimestamps() {
            Payment payment = createTestPayment();
            LocalDateTime beforeCreation = LocalDateTime.now();

            Payment paymentWithFraud = payment.withFraudEngineResponse(PaymentStatus.APPROVED, "ok");
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(PaymentStatus.APPROVED, "ok");
            Payment finalPayment = paymentWithRisk.withPaymentGatewayResponse(PaymentStatus.COMPLETED, "ok");

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getCreatedAt()).isAfterOrEqualTo(beforeCreation);
                    assertThat(result.getUpdatedAt()).isAfterOrEqualTo(result.getCreatedAt());
                })
                .verifyComplete();
        }
    }
}