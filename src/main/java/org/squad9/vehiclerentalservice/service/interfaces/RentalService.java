package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.dto.request.RentalRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.RentalResponseDTO;

import java.util.List;
import java.util.UUID;

public interface RentalService {
    List<RentalResponseDTO> findAll();
    RentalResponseDTO findById(UUID id);
    List<RentalResponseDTO> findByDriverEmail(String email);
    RentalResponseDTO save(RentalRequestDTO request);
    void delete(UUID id);
    RentalResponseDTO update(UUID id, RentalRequestDTO request);
}
