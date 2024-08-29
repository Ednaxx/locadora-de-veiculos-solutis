package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.RentalModel;

import java.util.List;

public interface RentalService {
    RentalModel save(RentalModel aluguel);
    List<RentalModel> findAll();
}
