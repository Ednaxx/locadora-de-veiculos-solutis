package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.InsurancePolicyModel;
import org.squad9.vehiclerentalservice.repository.InsurancePolicyRepository;
import org.squad9.vehiclerentalservice.service.interfaces.InsurancePolicyService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InsurancePolicyServiceImpl implements InsurancePolicyService {
    private InsurancePolicyRepository insurancePolicyRepository;

    @Override
    public List<InsurancePolicyModel> findAll() {
        return insurancePolicyRepository.findAll();
    }

    @Override
    public InsurancePolicyModel findById(UUID id) {
        return insurancePolicyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Apólice de seguro não encontrada com o ID: " + id));
    }

    @Override
    public InsurancePolicyModel save(InsurancePolicyModel apoliceSeguro) {
        try {
            return insurancePolicyRepository.save(apoliceSeguro);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void remove(UUID id) {
        try {
            insurancePolicyRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover apólice de seguro: " + e.getMessage());
        }
    }

    @Override
    public InsurancePolicyModel update(UUID id, InsurancePolicyModel insurancePolicy) {
        try {
            InsurancePolicyModel existingPolicy = insurancePolicyRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Apólice de seguro não encontrada com o ID: " + id));

            existingPolicy.setFranchiseValue(insurancePolicy.getFranchiseValue());
            existingPolicy.setThirdPartyProtection(insurancePolicy.isThirdPartyProtection());
            existingPolicy.setNaturalCausesProtection(insurancePolicy.isNaturalCausesProtection());
            existingPolicy.setTheftProtection(insurancePolicy.isTheftProtection());

            return insurancePolicyRepository.save(existingPolicy);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar apólice de seguro: " + e.getMessage());
        }
    }

}
