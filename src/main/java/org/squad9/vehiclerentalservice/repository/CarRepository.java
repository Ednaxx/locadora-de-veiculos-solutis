package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.CarModelModel;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarModel, UUID> {
    boolean existsByLicensePlate(String placa);

    boolean existsByChassi(String chassi);

    List<CarModel> findByCarModel(CarModelModel modeloCarroId);

    List<CarModel> findByAccessoriesContaining(AccessoryModel acessorio);
}
