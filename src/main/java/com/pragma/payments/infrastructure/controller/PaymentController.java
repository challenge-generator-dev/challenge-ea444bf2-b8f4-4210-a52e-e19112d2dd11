package com.pragma.payments.infrastructure.controller;

import com.pragma.payments.application.PaymentService;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.infrastructure.controller.dto.CreatePaymentRequest;
import com.pragma.payments.infrastructure.controller.dto.PaymentResponse;
import com.pragma.payments.infrastructure.controller.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Payments", description = "API para gestión de pagos")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo pago", description = "Crea un nuevo pago y lo procesa a través del motor de fraude, buró de riesgos y gateway de pagos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pago creado exitosamente",
                     content = @Content(schema = @Schema(implementation = PaymentResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<PaymentResponse> createPayment(
            @Parameter(description = "Datos del pago a crear") @RequestBody CreatePaymentRequest request) {
        logger.info("Received payment creation request for customer: {} and merchant: {}",
                request.getCustomerId(), request.getMerchantId());
        
        try {
            Payment payment = paymentService.createPayment(
                    request.getReferenceNumber(),
                    request.getCustomerId(),
                    request.getMerchantId(),
                    request.getAmount(),
                    request.getCurrency(),
                    request.getPaymentMethod()
            );
            
            logger.info("Payment created successfully with ID: {}", payment.getPaymentId());
            return ResponseEntity.status(HttpStatus.CREATED).body(PaymentResponse.fromDomain(payment));
            
        } catch (Exception e) {
            logger.error("Error creating payment: {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/{paymentId}")
    @Operation(summary = "Obtener un pago por ID", description = "Recupera los detalles de un pago específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado",
                     content = @Content(schema = @Schema(implementation = PaymentResponse.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<PaymentResponse> getPayment(
            @Parameter(description = "ID del pago") @PathVariable UUID paymentId) {
        logger.debug("Fetching payment with ID: {}", paymentId);
        
        Payment payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(PaymentResponse.fromDomain(payment));
    }

    @GetMapping
    @Operation(summary = "Listar todos los pagos", description = "Obtiene una lista de todos los pagos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos",
                     content = @Content(schema = @Schema(implementation = PaymentResponse.class)))
    })
    public ResponseEntity<List<PaymentResponse>> getAllPayments(
            @Parameter(description = "Número de página") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página") @RequestParam(defaultValue = "20") int size) {
        logger.debug("Fetching payments - page: {}, size: {}", page, size);
        
        List<Payment> payments = paymentService.getAllPayments(page, size);
        List<PaymentResponse> responses = payments.stream()
                .map(PaymentResponse::fromDomain)
                .toList();
        
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Listar pagos por cliente", description = "Obtiene todos los pagos de un cliente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos del cliente",
                     content = @Content(schema = @Schema(implementation = PaymentResponse.class)))
    })
    public ResponseEntity<List<PaymentResponse>> getPaymentsByCustomer(
            @Parameter(description = "ID del cliente") @PathVariable String customerId) {
        logger.debug("Fetching payments for customer: {}", customerId);
        
        List<Payment> payments = paymentService.getPaymentsByCustomerId(customerId);
        List<PaymentResponse> responses = payments.stream()
                .map(PaymentResponse::fromDomain)
                .toList();
        
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/merchant/{merchantId}")
    @Operation(summary = "Listar pagos por comercio", description = "Obtiene todos los pagos de un comercio específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos del comercio",
                     content = @Content(schema = @Schema(implementation = PaymentResponse.class)))
    })
    public ResponseEntity<List<PaymentResponse>> getPaymentsByMerchant(
            @Parameter(description = "ID del comercio") @PathVariable String merchantId) {
        logger.debug("Fetching payments for merchant: {}", merchantId);
        
        List<Payment> payments = paymentService.getPaymentsByMerchantId(merchantId);
        List<PaymentResponse> responses = payments.stream()
                .map(PaymentResponse::fromDomain)
                .toList();
        
        return ResponseEntity.ok(responses);
    }
}