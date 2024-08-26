package org.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.system.entity.Acessorio;
import org.system.entity.Carro;
import org.system.entity.ModeloCarro;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    boolean existsByPlaca(String placa);

    boolean existsByChassi(String chassi);

    List<Carro> findByModeloCarro(ModeloCarro modeloCarroId);

    List<Carro> findByAcessoriosContaining(Acessorio acessorio);
}
