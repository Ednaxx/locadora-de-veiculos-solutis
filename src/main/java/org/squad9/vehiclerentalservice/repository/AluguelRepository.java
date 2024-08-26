package org.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.system.entity.Aluguel;
import org.system.entity.Motorista;

import java.util.List;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    List<Aluguel> findByMotorista(Motorista motorista);
}