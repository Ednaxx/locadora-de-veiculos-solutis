package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.repository.AccessoryRepository;
import org.squad9.vehiclerentalservice.service.interfaces.AccessoryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {
    private AccessoryRepository accessoryRepository;

    @Override
    public List<AccessoryModel> findAll() {
        return accessoryRepository.findAll();
    }

    @Override
    public AccessoryModel findById(UUID id){
        try{
            Optional<AccessoryModel> acessorioOptional = accessoryRepository.findById(id);
            if (acessorioOptional.isPresent()){
                return acessorioOptional.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public AccessoryModel save(AccessoryModel acessorio) {
        try {
            return accessoryRepository.save(acessorio);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void remove(UUID id){
        if(!accessoryRepository.existsById(id)){
            throw new IllegalArgumentException();
        }

        try {
            accessoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover acessório: " + e.getMessage());
        }
    }
}
