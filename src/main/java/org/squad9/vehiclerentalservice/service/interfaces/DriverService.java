package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.DriverModel;

import java.util.List;
import java.util.UUID;

public interface DriverService {
    List<DriverModel> findAll();
    DriverModel findByEmail(String email);
    DriverModel findById(UUID id);
    DriverModel save(DriverModel driver);
    void remove (UUID id);
    DriverModel update(UUID id, DriverModel driver);
}
