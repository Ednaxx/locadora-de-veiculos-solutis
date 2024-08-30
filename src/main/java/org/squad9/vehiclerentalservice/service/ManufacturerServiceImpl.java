package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.ManufacturerModel;
import org.squad9.vehiclerentalservice.repository.ManufacturerRepository;
import org.squad9.vehiclerentalservice.service.interfaces.ManufacturerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {
    private ManufacturerRepository manufacturerRepository;

    @Override
    public ManufacturerModel save(ManufacturerModel fabricante) {
        try {
            return manufacturerRepository.save(fabricante);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ManufacturerModel> findAll() {
        try {
            return manufacturerRepository.findAll();
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ManufacturerModel findById(UUID id){
        try{
            Optional<ManufacturerModel> fabricanteOptional = manufacturerRepository.findById(id);
            if (fabricanteOptional.isPresent()){
                return fabricanteOptional.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public void remove(UUID id){
        try {
            manufacturerRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover fabricante: " + e.getMessage());
        }
    }

    @Override
    public ManufacturerModel update(UUID id, ManufacturerModel manufacturer) {
        // TODO: implement this
    }
}
