package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.request.InsurancePolicyRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.InsurancePolicyResponseDTO;
import org.squad9.vehiclerentalservice.model.InsurancePolicyModel;
import org.squad9.vehiclerentalservice.repository.InsurancePolicyRepository;
import org.squad9.vehiclerentalservice.service.interfaces.InsurancePolicyService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InsurancePolicyServiceImpl implements InsurancePolicyService {
    private final InsurancePolicyRepository insurancePolicyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<InsurancePolicyResponseDTO> findAll() {
        List<InsurancePolicyModel> insurancePolicies = insurancePolicyRepository.findAll();
        List<InsurancePolicyResponseDTO> response = new ArrayList<>();

        insurancePolicies.forEach(insurancePolicy -> response.add(modelMapper.map(insurancePolicy, InsurancePolicyResponseDTO.class)));
        return response;
    }

    @Override
    public InsurancePolicyResponseDTO findById(UUID id) {
        InsurancePolicyModel insurancePolicy = insurancePolicyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Apólice de seguro não encontrada com o ID: " + id));

        return modelMapper.map(insurancePolicy, InsurancePolicyResponseDTO.class);
    }

    @Override
    public InsurancePolicyResponseDTO save(InsurancePolicyRequestDTO request) {
        InsurancePolicyModel insurancePolicyToSave = modelMapper.map(request, InsurancePolicyModel.class);
        InsurancePolicyModel savedInsurancePolicy = insurancePolicyRepository.save(insurancePolicyToSave);

        return modelMapper.map(savedInsurancePolicy, InsurancePolicyResponseDTO.class);
    }

    @Override
    public void remove(UUID id) {
        if (!insurancePolicyRepository.existsById(id)) {
            throw new IllegalArgumentException("Apólice de seguro não encontrada com o ID: " + id);
        }
        insurancePolicyRepository.deleteById(id);
    }

    @Override
    public InsurancePolicyResponseDTO update(UUID id, InsurancePolicyRequestDTO request) {
        insurancePolicyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Apólice de seguro não encontrada com o ID: " + id));

        InsurancePolicyModel insurancePolicyToUpdate = modelMapper.map(request, InsurancePolicyModel.class);
        insurancePolicyToUpdate.setId(id);
        InsurancePolicyModel updatedInsurancePolicy = insurancePolicyRepository.save(insurancePolicyToUpdate);

        return modelMapper.map(updatedInsurancePolicy, InsurancePolicyResponseDTO.class);
    }

}
