package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.PaymentModel;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentModel, UUID> {
}
