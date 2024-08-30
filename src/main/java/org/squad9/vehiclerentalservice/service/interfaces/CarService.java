package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.util.Category;

import java.util.List;
import java.util.UUID;

public interface CarService {
    List<CarModel> findAll();
    CarModel findById(UUID id);

    List<CarModel> findAvailableOnDate(String startDate, String returnDate);
    List<CarModel> findByCategory(Category category);
    List<CarModel> findByCarModel(UUID model_id);
    List<CarModel> findByAcessorio(UUID accessorio_id);

    CarModel save(CarModel car);
    void remove(UUID id);
    CarModel update(UUID id, CarModel car);
}
