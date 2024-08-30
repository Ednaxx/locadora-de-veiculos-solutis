package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.DriverModel;

import java.util.List;
import java.util.UUID;

public interface DriverService {
    List<DriverModel> findAll();
    DriverModel save(DriverModel motorista);
    void remove (UUID motorista);
    DriverModel findByEmail(String email);
}
