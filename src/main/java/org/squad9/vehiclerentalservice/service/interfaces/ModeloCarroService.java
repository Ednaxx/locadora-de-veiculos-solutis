package org.system.service.interfaces;

import org.system.entity.ModeloCarro;

import java.util.List;

public interface ModeloCarroService {
    ModeloCarro save(ModeloCarro modeloCarro);
    List<ModeloCarro> findAll();
    ModeloCarro findById(Long id);
    void remove(Long id);
}
