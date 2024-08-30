package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.ManufacturerModel;

import java.util.List;
import java.util.UUID;

public interface ManufacturerService {
    List<ManufacturerModel> findAll();
    ManufacturerModel findById(UUID id);
    ManufacturerModel save(ManufacturerModel manufacturer);
    void remove(UUID id);
    ManufacturerModel update(UUID id, ManufacturerModel manufacturer);
}
