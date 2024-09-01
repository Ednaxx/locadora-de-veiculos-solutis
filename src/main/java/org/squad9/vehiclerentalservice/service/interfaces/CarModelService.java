package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.dto.request.CarModelRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.CarModelResponseDTO;
import org.squad9.vehiclerentalservice.enums.Category;

import java.util.List;
import java.util.UUID;

public interface CarModelService {
    List<CarModelResponseDTO> findAll();

    CarModelResponseDTO findById(UUID id);

    List<CarModelResponseDTO> findByCategoria(Category category);

    CarModelResponseDTO save(CarModelRequestDTO request);

    void remove(UUID id);

    CarModelResponseDTO update(UUID id, CarModelRequestDTO request);
}
