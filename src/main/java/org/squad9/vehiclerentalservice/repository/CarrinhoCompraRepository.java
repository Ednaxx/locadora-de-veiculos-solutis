package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.CarrinhoCompraModel;
import org.squad9.vehiclerentalservice.model.MotoristaModel;

import java.util.UUID;

public interface CarrinhoCompraRepository extends JpaRepository<CarrinhoCompraModel, UUID> {
    CarrinhoCompraModel findByMotorista(MotoristaModel motorista);
}
