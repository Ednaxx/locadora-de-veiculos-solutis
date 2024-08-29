package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.ManufacturerModel;

import java.util.List;
import java.util.UUID;

public interface ManufacturerService {
    ManufacturerModel save(ManufacturerModel fabricante);
    List<ManufacturerModel> findAll();
    ManufacturerModel findById(UUID id);
    void remove(UUID id);
}
