package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.dto.request.ManufacturerRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.ManufacturerResponseDTO;
import org.squad9.vehiclerentalservice.model.ManufacturerModel;

import java.util.List;
import java.util.UUID;

public interface ManufacturerService {
    List<ManufacturerResponseDTO> findAll();
    ManufacturerResponseDTO findById(UUID id);
    ManufacturerResponseDTO save(ManufacturerRequestDTO request);
    void remove(UUID id);
    ManufacturerResponseDTO update(UUID id, ManufacturerRequestDTO request);
}
