package org.system.service.interfaces;

import org.system.entity.Fabricante;

import java.util.List;

public interface FabricanteService {
    Fabricante save(Fabricante fabricante);
    List<Fabricante> findAll();
    Fabricante findById(Long id);
    void remove(Long id);
}
