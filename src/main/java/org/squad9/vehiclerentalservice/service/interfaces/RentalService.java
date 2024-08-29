package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.RentalModel;

import java.util.List;

public interface RentalService {
    public RentalModel save(RentalModel aluguel);
    public List<RentalModel> findAll();
}
