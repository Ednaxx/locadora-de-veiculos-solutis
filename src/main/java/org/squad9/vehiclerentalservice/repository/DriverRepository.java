package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.DriverModel;

import java.util.UUID;

public interface DriverRepository extends JpaRepository<DriverModel, UUID> {
    DriverModel findByEmail(String email);
    DriverModel findByCpf(String cpf);
    DriverModel findByCNH(String numeroCNH);
}