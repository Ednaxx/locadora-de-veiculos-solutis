package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.InsurancePolicyModel;

import java.util.List;
import java.util.UUID;

public interface InsurancePolicyService {
    List<InsurancePolicyModel> findAll();
    InsurancePolicyModel findById(UUID id);
    InsurancePolicyModel save(InsurancePolicyModel insurancePolicy);
    void remove(UUID id);
    InsurancePolicyModel update(UUID id, InsurancePolicyModel insurancePolicy);
}
