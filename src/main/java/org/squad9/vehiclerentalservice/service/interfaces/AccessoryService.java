package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.AccessoryModel;

import java.util.List;
import java.util.UUID;

public interface AccessoryService {
    AccessoryModel findById(UUID id);

    AccessoryModel save(AccessoryModel acessorio);
    List<AccessoryModel> findAll();
    void remove(UUID id);
}
