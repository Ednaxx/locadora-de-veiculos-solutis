package org.system.service.interfaces;

import org.system.entity.Carro;

import java.util.List;

public interface CarroService {
    Carro save(Carro carro);
    List<Carro> findAll();
    Carro findById(Long id);
    void remove(Long id);
}
