package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.FabricanteModel;
import org.squad9.vehiclerentalservice.repository.FabricanteRepository;
import org.squad9.vehiclerentalservice.service.interfaces.FabricanteService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FabricanteServiceImpl implements FabricanteService {
    private FabricanteRepository fabricanteRepository;

    @Override
    public FabricanteModel save(FabricanteModel fabricante) {
        try {
            return fabricanteRepository.save(fabricante);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<FabricanteModel> findAll() {
        try {
            return fabricanteRepository.findAll();
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public FabricanteModel findById(UUID id){
        try{
            Optional<FabricanteModel> fabricanteOptional = fabricanteRepository.findById(id);
            if (fabricanteOptional.isPresent()){
                FabricanteModel fabricante = fabricanteOptional.get();
                return fabricante;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
    @Override
    public void remove(UUID id){
        try {
            fabricanteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover fabricante: " + e.getMessage());
        }
    }
}
