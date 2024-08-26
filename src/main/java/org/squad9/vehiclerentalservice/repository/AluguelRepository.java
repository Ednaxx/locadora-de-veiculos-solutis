package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.AluguelModel;
import org.squad9.vehiclerentalservice.model.MotoristaModel;

import java.util.List;

public interface AluguelRepository extends JpaRepository<AluguelModel, Long> {
    List<AluguelModel> findByMotorista(MotoristaModel motorista);
}