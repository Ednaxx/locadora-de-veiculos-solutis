package org.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.system.entity.Motorista;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    Motorista findByEmail(String email);
    Motorista findByCpf(String cpf);
    Motorista findBynumeroCNH(String numeroCNH);
}
