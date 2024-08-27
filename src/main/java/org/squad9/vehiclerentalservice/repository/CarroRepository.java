package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.AcessorioModel;
import org.squad9.vehiclerentalservice.model.CarroModel;
import org.squad9.vehiclerentalservice.model.ModeloCarroModel;

import java.util.List;
import java.util.UUID;

public interface CarroRepository extends JpaRepository<CarroModel, UUID> {
    boolean existsByPlaca(String placa);

    boolean existsByChassi(String chassi);

    List<CarroModel> findByModeloCarro(ModeloCarroModel modeloCarroId);

    List<CarroModel> findByAcessoriosContaining(AcessorioModel acessorio);
}
