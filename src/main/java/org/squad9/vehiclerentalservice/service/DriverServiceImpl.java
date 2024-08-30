package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.repository.DriverRepository;
import org.squad9.vehiclerentalservice.repository.ShoppingCartRepository;
import org.squad9.vehiclerentalservice.service.interfaces.DriverService;
import org.squad9.vehiclerentalservice.service.util.CPFValidation;
import org.squad9.vehiclerentalservice.service.util.DriverValidations;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository;
    private final ShoppingCartRepository shoppingCartRepository;

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
            if (!CPFValidation.isCPF(motorista.getCPF())) {
                throw new IllegalArgumentException("CPF inválido");
            }

            if (!DriverValidations.isCNH(motorista.getCNH())) {
                throw new IllegalArgumentException("CNH inválida");
            }

            if (existCPF(motorista.getCPF())) {
                throw new IllegalArgumentException("CPF já existente no sistema!");
            }
            if (existCNH(motorista.getCPF())) {
                throw new IllegalArgumentException("CNH já existente no sistema!");
            }
            if (existEmail(motorista.getCPF())) {
                throw new IllegalArgumentException("Email já existente no sistema!");
            }

            motorista.setCPF(CPFValidation.formatCPF(motorista.getCPF()));

            ShoppingCartModel shoppingCart = new ShoppingCartModel();
            shoppingCart.setDriver(motorista);
            shoppingCart = shoppingCartRepository.save(shoppingCart);

            motorista.setShoppingCart(shoppingCart);

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
    public DriverModel findById(UUID id) {
        // TODO: implement this
    }

    @Override
    public void remove(UUID motoristaId){
        try {
            driverRepository.deleteById(motoristaId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover motorista: " + e.getMessage());
        }
    }


    @Override
    public DriverModel update(UUID id, DriverModel driver) {
        // TODO: implement this
    }

    private boolean existCPF(String cpf) {
        return driverRepository.findByCPF(cpf) != null;
    }

    private boolean existCNH(String numeroCNH) {
        return driverRepository.findByCNH(numeroCNH) != null;
    }

    private boolean existEmail(String email) {
        return (driverRepository.findByEmail(email) != null);
    }
}
