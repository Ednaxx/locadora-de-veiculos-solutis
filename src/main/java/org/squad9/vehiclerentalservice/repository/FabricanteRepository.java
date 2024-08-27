package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.FabricanteModel;

import java.util.UUID;

public interface FabricanteRepository extends JpaRepository<FabricanteModel, UUID> {
}
