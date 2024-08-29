package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.CarModel;

import java.util.List;
import java.util.UUID;

public interface CarService {
    CarModel save(CarModel carro);
    List<CarModel> findAll();
    CarModel findById(UUID id);
    void remove(UUID id);
}
