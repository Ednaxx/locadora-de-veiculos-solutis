package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.ModeloCarroModel;
import org.squad9.vehiclerentalservice.model.util.Categoria;

import java.util.List;

public interface ModeloCarroRepository extends JpaRepository<ModeloCarroModel, Long> {
    List<ModeloCarroModel> findByCategoria(Categoria categoria);
}
