package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.CarroModel;

import java.util.List;
import java.util.UUID;

public interface CarroService {
    CarroModel save(CarroModel carro);
    List<CarroModel> findAll();
    CarroModel findById(UUID id);
    void remove(UUID id);
}
