package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.AccessoryModel;

import java.util.UUID;

public interface AccessoryRepository extends JpaRepository<AccessoryModel, UUID> {
}
