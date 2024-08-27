package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.ApoliceSeguroModel;
import org.squad9.vehiclerentalservice.repository.ApolicesRepository;
import org.squad9.vehiclerentalservice.service.interfaces.ApolicesService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ApolicesServiceImpl implements ApolicesService {
    private ApolicesRepository apolicesRepository;

    @Override
    public List<ApoliceSeguroModel> findAll() {
        return apolicesRepository.findAll();
    }

    @Override
    public ApoliceSeguroModel findById(UUID id) {
        try{
            Optional<ApoliceSeguroModel> apoliceSeguroOptional = apolicesRepository.findById(id);
            if (apoliceSeguroOptional.isPresent()) return apoliceSeguroOptional.get();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public ApoliceSeguroModel save(ApoliceSeguroModel apoliceSeguro) {
        try {
            return apolicesRepository.save(apoliceSeguro);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void remove(UUID id) {
        try {
            apolicesRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover apólice de seguro: " + e.getMessage());
        }
    }
}
