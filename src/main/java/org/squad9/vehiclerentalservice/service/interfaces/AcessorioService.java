package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.AcessorioModel;

import java.util.List;
import java.util.UUID;

public interface AcessorioService {
    AcessorioModel findById(UUID id);

    AcessorioModel save(AcessorioModel acessorio);
    List<AcessorioModel> findAll();
    void remove(UUID id);
}
