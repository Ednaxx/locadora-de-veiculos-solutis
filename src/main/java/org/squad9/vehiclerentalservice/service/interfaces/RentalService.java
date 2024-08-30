package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.RentalModel;

import java.util.List;
import java.util.UUID;

public interface RentalService {
    List<RentalModel> findAll();
    RentalModel findById(UUID id);
    RentalModel save(RentalModel rental);
    void delete(UUID id);
    RentalModel update(UUID id, RentalModel rental);
}
