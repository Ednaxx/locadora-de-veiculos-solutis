package org.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.system.entity.Categoria;
import org.system.entity.ModeloCarro;

import java.util.List;

public interface ModeloCarroRepository extends JpaRepository<ModeloCarro, Long> {
    List<ModeloCarro> findByCategoria(Categoria categoria);
}
