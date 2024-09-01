package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.enums.PaymentMethods;

import java.util.UUID;

public interface PaymentService {
    void processPayment(UUID rentalId, PaymentMethods paymentMethod);
}
