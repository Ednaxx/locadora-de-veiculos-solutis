package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.repository.DriverRepository;
import org.squad9.vehiclerentalservice.repository.ShoppingCartRepository;
import org.squad9.vehiclerentalservice.service.interfaces.DriverService;
import org.squad9.vehiclerentalservice.service.util.CPFValidation;
import org.squad9.vehiclerentalservice.service.util.DriverValidations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<DriverModel> findAll() {
        try {
            return driverRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível encontrar registros de motoristas: " + e.getMessage());
        }
    }

    @Override
    public DriverModel findByEmail(String email) {
        return driverRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Motorista não encontrado com o email: " + email));
    }

    @Override
    public DriverModel findById(UUID id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Motorista não encontrado com o ID: " + id));
    }

    @Override
    public void remove(UUID driverId) {
        try {
            if (!driverRepository.existsById(driverId)) {
                throw new IllegalArgumentException("Motorista não encontrado com o ID: " + driverId);
            }
            driverRepository.deleteById(driverId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover motorista: " + e.getMessage());
        }
    }

    @Override
    public DriverModel save(DriverModel driver) {
        try {
            if (!CPFValidation.isCPF(driver.getCPF())) {
                throw new IllegalArgumentException("CPF inválido");
            }
            if (!DriverValidations.isCNH(driver.getCNH())) {
                throw new IllegalArgumentException("CNH inválida");
            }

            if (existCPF(driver.getCPF())) {
                throw new IllegalArgumentException("CPF já existente no sistema!");
            }
            if (existCNH(driver.getCNH())) {
                throw new IllegalArgumentException("CNH já existente no sistema!");
            }
            if (existEmail(driver.getEmail())) {
                throw new IllegalArgumentException("Email já existente no sistema!");
            }

            driver.setCPF(CPFValidation.formatCPF(driver.getCPF()));

            DriverModel savedDriver = driverRepository.save(driver);

            ShoppingCartModel shoppingCart = new ShoppingCartModel();
            shoppingCart.setDriver(savedDriver);
            shoppingCart = shoppingCartRepository.save(shoppingCart);

            savedDriver.setShoppingCart(shoppingCart);
            driverRepository.save(savedDriver);

            return savedDriver;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar motorista: " + e.getMessage());
        }
    }


    @Override
    public DriverModel update(UUID id, DriverModel driver) {
        try {
            DriverModel existingDriver = driverRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Motorista não encontrado com o ID: " + id));

            existingDriver.setName(driver.getName());
            existingDriver.setCPF(driver.getCPF());
            existingDriver.setCNH(driver.getCNH());
            existingDriver.setEmail(driver.getEmail());
            existingDriver.setShoppingCart(driver.getShoppingCart());

            return driverRepository.save(existingDriver);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar motorista: " + e.getMessage());
        }
    }

    private boolean existCPF(String cpf) {
        return driverRepository.findByCPF(cpf) != null;
    }

    private boolean existCNH(String numeroCNH) {
        return driverRepository.findByCNH(numeroCNH) != null;
    }

    private boolean existEmail(String email) {
        return driverRepository.findByEmail(email).isPresent();
    }
}
