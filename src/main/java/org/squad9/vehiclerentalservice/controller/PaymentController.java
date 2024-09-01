package org.squad9.vehiclerentalservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.squad9.vehiclerentalservice.dto.request.PaymentRequestDTO;
import org.squad9.vehiclerentalservice.enums.PaymentMethods;
import org.squad9.vehiclerentalservice.service.interfaces.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/confirmar")
    public ResponseEntity<String> confirmPayment(@RequestBody @Valid PaymentRequestDTO request) {
        paymentService.processPayment(request.getRentalId(), request.getPaymentMethod(), request);
        return ResponseEntity.status(HttpStatus.OK).body("Pagamento confirmado com sucesso.");
    }

    @GetMapping("/metodos")
    public ResponseEntity<PaymentMethods[]> getPaymentMethods() {
        PaymentMethods[] methods = PaymentMethods.values();
        return ResponseEntity.ok(methods);
    }
}
