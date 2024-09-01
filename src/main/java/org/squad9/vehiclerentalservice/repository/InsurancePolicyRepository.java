package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.InsurancePolicyModel;

import java.util.UUID;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicyModel, UUID> {
}
