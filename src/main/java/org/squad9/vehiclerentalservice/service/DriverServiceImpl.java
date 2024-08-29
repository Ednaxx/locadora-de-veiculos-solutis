package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.repository.DriverRepository;
import org.squad9.vehiclerentalservice.service.interfaces.DriverService;
import org.squad9.vehiclerentalservice.service.util.CNHValidation;
import org.squad9.vehiclerentalservice.service.util.CPFValidation;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository;

    @Override
    public List<DriverModel> findAll() {
        try {
            return driverRepository.findAll();
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public DriverModel save(DriverModel motorista) {
        try {
            if (!CPFValidation.isCPF(motorista.getCpf())){
                throw new IllegalArgumentException("CPF inválido");
            }

            if (!CNHValidation.isCNH(motorista.getCNH_number())) {
                throw new IllegalArgumentException("CNH inválida");
            }

            if (existCPF(motorista.getCpf())) {
                throw new IllegalArgumentException("CPF já existente no sistema!");
            }
            if (existCNH(motorista.getCpf())) {
                throw new IllegalArgumentException("CNH já existente no sistema!");
            }
            if (existEmail(motorista.getCpf())) {
                throw new IllegalArgumentException("Email já existente no sistema!");
            }

            motorista.setCpf(CPFValidation.formatCPF(motorista.getCpf()));

            return driverRepository.save(motorista);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public DriverModel findByEmail(@PathVariable String email){
        return driverRepository.findByEmail(email);
    }

    @Override
    public void remove(UUID motoristaId){
        try {
            driverRepository.deleteById(motoristaId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover motorista: " + e.getMessage());
        }
    }

    private boolean existCPF(String cpf){
        return driverRepository.findByCpf(cpf) != null;
    }

    private boolean existCNH(String numeroCNH){
        return driverRepository.findBynumeroCNH(numeroCNH) != null;
    }

    private boolean existEmail(String email){
        return (driverRepository.findByEmail(email) != null);
    }
}
