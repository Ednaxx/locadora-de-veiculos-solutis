package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.squad9.vehiclerentalservice.model.MotoristaModel;
import org.squad9.vehiclerentalservice.repository.MotoristaRepository;
import org.squad9.vehiclerentalservice.service.interfaces.MotoristaService;
import org.squad9.vehiclerentalservice.service.util.CNHValidation;
import org.squad9.vehiclerentalservice.service.util.CPFValidation;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MotoristaServiceImpl implements MotoristaService {
    private MotoristaRepository motoristaRepository;

    @Override
    public List<MotoristaModel> findAll() {
        try {
            return motoristaRepository.findAll();
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public MotoristaModel save(MotoristaModel motorista) {
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

            return motoristaRepository.save(motorista);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public MotoristaModel findByEmail(@PathVariable String email){
        return motoristaRepository.findByEmail(email);
    }

    @Override
    public void remove(UUID motoristaId){
        try {
            motoristaRepository.deleteById(motoristaId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover motorista: " + e.getMessage());
        }
    }

    private boolean existCPF(String cpf){
        return motoristaRepository.findByCpf(cpf) != null;
    }

    private boolean existCNH(String numeroCNH){
        return motoristaRepository.findBynumeroCNH(numeroCNH) != null;
    }

    private boolean existEmail(String email){
        return (motoristaRepository.findByEmail(email) != null);
    }
}
