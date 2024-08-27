package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.ModeloCarroModel;

import java.util.List;
import java.util.UUID;

public interface ModeloCarroService {
    ModeloCarroModel save(ModeloCarroModel modeloCarro);
    List<ModeloCarroModel> findAll();
    ModeloCarroModel findById(UUID id);
    void remove(UUID id);
}
