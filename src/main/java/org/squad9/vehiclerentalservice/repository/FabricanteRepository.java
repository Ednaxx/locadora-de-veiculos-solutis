package org.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.system.entity.Fabricante;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
}
