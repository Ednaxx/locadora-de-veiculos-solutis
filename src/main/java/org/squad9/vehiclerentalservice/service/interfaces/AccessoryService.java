package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.AccessoryModel;

import java.util.List;
import java.util.UUID;

public interface AccessoryService {
    List<AccessoryModel> findAll();
    AccessoryModel findById(UUID id);
    AccessoryModel save(AccessoryModel accessory);
    void remove(UUID id);
    AccessoryModel update(UUID id, AccessoryModel accessory);
}
