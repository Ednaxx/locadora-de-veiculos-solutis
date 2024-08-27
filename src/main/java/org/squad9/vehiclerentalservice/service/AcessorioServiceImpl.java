package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.AcessorioModel;
import org.squad9.vehiclerentalservice.repository.AcessorioRepository;
import org.squad9.vehiclerentalservice.service.interfaces.AcessorioService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AcessorioServiceImpl implements AcessorioService {
    private AcessorioRepository acessorioRepository;

    @Override
    public List<AcessorioModel> findAll() {
        return acessorioRepository.findAll();
    }

    @Override
    public AcessorioModel findById(UUID id){
        try{
            Optional<AcessorioModel> acessorioOptional = acessorioRepository.findById(id);
            if (acessorioOptional.isPresent()){
                return acessorioOptional.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public AcessorioModel save(AcessorioModel acessorio) {
        try {
            return acessorioRepository.save(acessorio);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void remove(UUID id){
        if(!acessorioRepository.existsById(id)){
            throw new IllegalArgumentException();
        }

        try {
            acessorioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover acessório: " + e.getMessage());
        }
    }
}
