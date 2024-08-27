package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.ModeloCarroModel;
import org.squad9.vehiclerentalservice.model.util.Categoria;

import java.util.List;
import java.util.UUID;

public interface ModeloCarroRepository extends JpaRepository<ModeloCarroModel, UUID> {
    List<ModeloCarroModel> findByCategoria(Categoria categoria);
}
