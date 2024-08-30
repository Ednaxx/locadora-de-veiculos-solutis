package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.dto.request.CarRequestDTO;
import org.squad9.vehiclerentalservice.dto.request.DateIntervalRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.CarResponseDTO;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.util.Category;

import java.util.List;
import java.util.UUID;

public interface CarService {
    List<CarResponseDTO> findAll();
    CarResponseDTO findById(UUID id);

    List<CarResponseDTO> findAvailableOnDate(DateIntervalRequestDTO request);
    List<CarResponseDTO> findByCategory(Category category);
    List<CarResponseDTO> findByCarModel(UUID model_id);
    List<CarResponseDTO> findByAcessorio(UUID accessorio_id);

    CarResponseDTO save(CarRequestDTO request);
    void remove(UUID id);
    CarResponseDTO update(UUID id, CarRequestDTO request);
}
