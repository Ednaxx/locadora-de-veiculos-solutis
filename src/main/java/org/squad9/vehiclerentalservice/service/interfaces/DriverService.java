package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.dto.request.DriverRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.DriverResponseDTO;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;

import java.util.List;
import java.util.UUID;

public interface DriverService {
    List<DriverResponseDTO> findAll();
    DriverResponseDTO findByEmail(String email);
    DriverResponseDTO findById(UUID id);
    DriverResponseDTO save(DriverRequestDTO request);
    void remove (UUID id);
    DriverResponseDTO update(UUID id, DriverRequestDTO request);
}
