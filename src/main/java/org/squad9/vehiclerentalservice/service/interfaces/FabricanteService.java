package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.FabricanteModel;

import java.util.List;
import java.util.UUID;

public interface FabricanteService {
    FabricanteModel save(FabricanteModel fabricante);
    List<FabricanteModel> findAll();
    FabricanteModel findById(UUID id);
    void remove(UUID id);
}
