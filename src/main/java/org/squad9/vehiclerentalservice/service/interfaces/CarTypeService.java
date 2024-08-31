package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.dto.request.CarTypeRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.CarTypeResponseDTO;
import org.squad9.vehiclerentalservice.model.util.Category;

import java.util.List;
import java.util.UUID;

public interface CarTypeService {
    List<CarTypeResponseDTO> findAll();
    CarTypeResponseDTO findById(UUID id);
    List<CarTypeResponseDTO> findByCategoria(Category category);
    CarTypeResponseDTO save(CarTypeRequestDTO request);
    void remove(UUID id);
    CarTypeResponseDTO update(UUID id, CarTypeRequestDTO request);
}
