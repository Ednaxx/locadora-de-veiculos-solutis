package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.InsurancePolicyModel;

import java.util.List;
import java.util.UUID;

public interface InsurancePolicyService {
    InsurancePolicyModel findById(UUID id);
    InsurancePolicyModel save(InsurancePolicyModel apoliceSeguro);
    List<InsurancePolicyModel> findAll();
    void remove(UUID id);
}
