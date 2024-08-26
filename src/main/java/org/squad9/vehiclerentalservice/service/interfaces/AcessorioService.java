package org.system.service.interfaces;

import org.system.entity.Acessorio;
import org.system.entity.Aluguel;

import java.util.List;
import java.util.Optional;

public interface AcessorioService {
    Acessorio findById(Long id);

    Acessorio save(Acessorio acessorio);
    List<Acessorio> findAll();
    void remove(Long id);
}
