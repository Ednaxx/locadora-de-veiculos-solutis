package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.CarModelModel;

import java.util.List;
import java.util.UUID;

public interface CarModelService {
    CarModelModel save(CarModelModel modeloCarro);
    List<CarModelModel> findAll();
    CarModelModel findById(UUID id);
    void remove(UUID id);
}
