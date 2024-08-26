package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.MotoristaModel;

public interface MotoristaRepository extends JpaRepository<MotoristaModel, Long> {
    MotoristaModel findByEmail(String email);
    MotoristaModel findByCpf(String cpf);
    MotoristaModel findBynumeroCNH(String numeroCNH);
}
