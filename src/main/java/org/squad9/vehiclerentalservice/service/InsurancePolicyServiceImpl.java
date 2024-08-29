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
        try{
            Optional<InsurancePolicyModel> apoliceSeguroOptional = insurancePolicyRepository.findById(id);
            if (apoliceSeguroOptional.isPresent()) return apoliceSeguroOptional.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
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
}
