package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.ManufacturerModel;

import java.util.UUID;

public interface ManufacturerRepository extends JpaRepository<ManufacturerModel, UUID> {
}
