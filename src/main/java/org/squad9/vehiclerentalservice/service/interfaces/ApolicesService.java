package org.system.service.interfaces;

import org.system.entity.ApoliceSeguro;

import java.util.List;

public interface ApolicesService {
    ApoliceSeguro findById(Long id);
    ApoliceSeguro save(ApoliceSeguro apoliceSeguro);
    List<ApoliceSeguro> findAll();
    void remove(Long id);
}
