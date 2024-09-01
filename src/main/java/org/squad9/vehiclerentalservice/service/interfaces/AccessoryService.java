package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.dto.request.AccessoryRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.AccessoryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AccessoryService {
    List<AccessoryResponseDTO> findAll();

    AccessoryResponseDTO findById(UUID id);

    AccessoryResponseDTO save(AccessoryRequestDTO request);

    void remove(UUID id);

    AccessoryResponseDTO update(UUID id, AccessoryRequestDTO request);
}
