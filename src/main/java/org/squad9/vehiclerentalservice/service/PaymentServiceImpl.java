package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.request.PaymentRequestDTO;
import org.squad9.vehiclerentalservice.enums.PaymentMethods;
import org.squad9.vehiclerentalservice.repository.RentalRepository;
import org.squad9.vehiclerentalservice.service.interfaces.PaymentService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final RentalRepository rentalRepository;

    @Override
    public void processPayment(UUID rentalId, PaymentMethods paymentMethod, PaymentRequestDTO request) {
       rentalRepository.findById(rentalId).orElseThrow(() -> new IllegalArgumentException("Aluguel não encontrado com o ID: " + rentalId));

        processPaymentData(rentalId, request);
    }

    private void processPaymentData(UUID rentalId, PaymentRequestDTO request) {
        System.out.println("Processando pagamento do aluguel de ID: " + rentalId);
        System.out.println("Método de pagamento: " + request.getPaymentMethod());
        System.out.println("Número do cartão: " + request.getCardNumber());
    }
}
