package org.system.service.interfaces;

import org.system.entity.Motorista;

import java.util.List;

public interface MotoristaService {
    List<Motorista> findAll();
    Motorista save(Motorista motorista);
    void remove (Long motorista);
    Motorista findByEmail(String email);
}
