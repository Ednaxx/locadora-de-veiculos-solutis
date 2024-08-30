package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.DriverModel;

import java.util.Optional;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<DriverModel, UUID> {
    Optional<DriverModel> findByEmail(String email);
    DriverModel findByCPF(String CPF);
    DriverModel findByCNH(String CNH);
}
