package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.dto.request.InsurancePolicyRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.InsurancePolicyResponseDTO;

import java.util.List;
import java.util.UUID;

public interface InsurancePolicyService {
    List<InsurancePolicyResponseDTO> findAll();

    InsurancePolicyResponseDTO findById(UUID id);

    InsurancePolicyResponseDTO save(InsurancePolicyRequestDTO request);

    void remove(UUID id);

    InsurancePolicyResponseDTO update(UUID id, InsurancePolicyRequestDTO request);
}
