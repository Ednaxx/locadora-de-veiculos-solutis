package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.enums.PaymentMethods;
import org.squad9.vehiclerentalservice.model.RentalModel;
import org.squad9.vehiclerentalservice.repository.RentalRepository;
import org.squad9.vehiclerentalservice.service.interfaces.PaymentService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final RentalRepository rentalRepository;

    @Override
    public void processPayment(UUID rentalId, PaymentMethods paymentMethod) {
       rentalRepository.findById(rentalId).orElseThrow(() -> new IllegalArgumentException("Aluguel não encontrado com o ID: " + rentalId));

        switch (paymentMethod) {
            case CARTAO_CREDITO:
                processCreditCardPayment(rentalId);
                break;
            case CARTAO_DEBITO:
                processDebitCardPayment(rentalId);
                break;
            case DINHEIRO:
                processCashPayment(rentalId);
                break;
            case BOLETO:
                processBoletoPayment(rentalId);
                break;
            case PIX:
                processPixPayment(rentalId);
                break;
            default:
                throw new IllegalArgumentException("Método de pagamento não suportado: " + paymentMethod);
        }
    }

    private void processCreditCardPayment(UUID rentalId) {
        // Implement logic for processing credit card payment
        System.out.println("Processing credit card payment for rental ID: " + rentalId);
        // Add specific logic here...
    }

    private void processDebitCardPayment(UUID rentalId) {
        // Implement logic for processing debit card payment
        System.out.println("Processing debit card payment for rental ID: " + rentalId);
        // Add specific logic here...
    }

    private void processCashPayment(UUID rentalId) {
        // Implement logic for processing cash payment
        System.out.println("Processing cash payment for rental ID: " + rentalId);
        // Add specific logic here...
    }

    private void processBoletoPayment(UUID rentalId) {
        // Implement logic for processing boleto payment
        System.out.println("Processing boleto payment for rental ID: " + rentalId);
        // Add specific logic here...
    }

    private void processPixPayment(UUID rentalId) {
        // Implement logic for processing PIX payment
        System.out.println("Processing PIX payment for rental ID: " + rentalId);
        // Add specific logic here...
    }
}
