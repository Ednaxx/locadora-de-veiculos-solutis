package org.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.entity.ApoliceSeguro;
import org.system.repository.ApolicesRepository;
import org.system.service.interfaces.ApolicesService;

import java.util.List;
import java.util.Optional;

@Service
public class ApolicesServiceImpl implements ApolicesService {
    @Autowired
    private ApolicesRepository apolicesRepository;

    @Override
    public List<ApoliceSeguro> findAll() {
        return apolicesRepository.findAll();
    }

    @Override
    public ApoliceSeguro findById(Long id) {
        try{
            Optional<ApoliceSeguro> apoliceSeguroOptional = apolicesRepository.findById(id);
            if (apoliceSeguroOptional.isPresent()){
                ApoliceSeguro apoliceSeguro = apoliceSeguroOptional.get();
                return apoliceSeguro;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public ApoliceSeguro save(ApoliceSeguro apoliceSeguro) {
        try {
            return apolicesRepository.save(apoliceSeguro);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void remove(Long id) {
        try {
            apolicesRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover apólice de seguro: " + e.getMessage());
        }
    }
}
