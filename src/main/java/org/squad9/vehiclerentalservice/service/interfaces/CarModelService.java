package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.CarModelModel;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CarModelService {
    List<CarModelModel> findAll();
    CarModelModel findById(UUID id);
    CarModelModel save(CarModelModel carModel);
    void remove(UUID id);
    CarModelModel update(UUID id, CarModelModel carModel);
}
