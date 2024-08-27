package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.ApoliceSeguroModel;

import java.util.UUID;

public interface ApolicesRepository extends JpaRepository<ApoliceSeguroModel, UUID> {
}
