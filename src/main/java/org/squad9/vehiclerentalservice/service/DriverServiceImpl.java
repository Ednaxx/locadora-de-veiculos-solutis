package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.request.DriverRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.DriverResponseDTO;
import org.squad9.vehiclerentalservice.exception.RestException;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.repository.DriverRepository;
import org.squad9.vehiclerentalservice.repository.ShoppingCartRepository;
import org.squad9.vehiclerentalservice.service.interfaces.DriverService;
import org.squad9.vehiclerentalservice.service.util.CPFValidation;
import org.squad9.vehiclerentalservice.service.util.DriverValidations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DriverResponseDTO> findAll() {
        List<DriverModel> drivers = driverRepository.findAll();
        List<DriverResponseDTO> response = new ArrayList<>();

        drivers.forEach(accessory -> response.add(modelMapper.map(accessory, DriverResponseDTO.class)));
        return response;
    }

    @Override
    public DriverResponseDTO findByEmail(String email) {
        DriverModel driver = driverRepository.findByEmail(email)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Motorista não encontrado com o email: " + email));

        return modelMapper.map(driver, DriverResponseDTO.class);
    }

    @Override
    public DriverResponseDTO findById(UUID id) {
        DriverModel driver = driverRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Motorista não encontrado com o ID: " + id));

        return modelMapper.map(driver, DriverResponseDTO.class);
    }

    @Override
    public void remove(UUID id) {
        if (!driverRepository.existsById(id)) {
            throw new RestException(HttpStatus.NOT_FOUND, "Motorista não encontrado com o ID: " + id);
        }
        driverRepository.deleteById(id);
    }

    @Override
    public DriverResponseDTO save(DriverRequestDTO request) {
        DriverModel driverToSave = modelMapper.map(request, DriverModel.class);

        if (!CPFValidation.isCPF(driverToSave.getCPF())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "CPF inválido");
        }
        if (!DriverValidations.isCNHValid(driverToSave.getCNH())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "CNH inválida");
        }

        if (existCPF(driverToSave.getCPF())) {
            throw new RestException(HttpStatus.CONFLICT, "CPF já existente no sistema!");
        }
        if (existCNH(driverToSave.getCNH())) {
            throw new RestException(HttpStatus.CONFLICT, "CNH já existente no sistema!");
        }
        if (existEmail(driverToSave.getEmail())) {
            throw new RestException(HttpStatus.CONFLICT, "Email já existente no sistema!");
        }

        ShoppingCartModel shoppingCart = new ShoppingCartModel();
        shoppingCart.setDriver(driverToSave);
        driverToSave.setShoppingCart(shoppingCart);

        DriverModel savedDriver = driverRepository.save(driverToSave);
        shoppingCartRepository.save(shoppingCart);

        return modelMapper.map(savedDriver, DriverResponseDTO.class);
    }

    @Override
    public DriverResponseDTO update(UUID id, DriverRequestDTO request) {
        DriverModel existingDriver = driverRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Motorista não encontrado com o ID: " + id));

        ShoppingCartModel existingCart = existingDriver.getShoppingCart();

        modelMapper.map(request, existingDriver);
        existingDriver.setShoppingCart(existingCart);

        DriverModel updatedDriver = driverRepository.save(existingDriver);
        shoppingCartRepository.save(existingCart);

        return modelMapper.map(updatedDriver, DriverResponseDTO.class);
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
