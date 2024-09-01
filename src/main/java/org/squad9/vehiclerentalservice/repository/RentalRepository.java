package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.model.RentalModel;

import java.util.List;
import java.util.UUID;

public interface RentalRepository extends JpaRepository<RentalModel, UUID> {
    List<RentalModel> findByDriver(DriverModel driver);

    List<RentalModel> findByDriverEmail(String email);
}