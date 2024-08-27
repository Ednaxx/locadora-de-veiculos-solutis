package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.AluguelModel;
import org.squad9.vehiclerentalservice.model.MotoristaModel;

import java.util.List;
import java.util.UUID;

public interface AluguelRepository extends JpaRepository<AluguelModel, UUID> {
    List<AluguelModel> findByMotorista(MotoristaModel motorista);
}