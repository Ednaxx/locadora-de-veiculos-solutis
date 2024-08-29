package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.model.DriverModel;

import java.util.UUID;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartModel, UUID> {
    ShoppingCartModel findByDriver(DriverModel motorista);
}
