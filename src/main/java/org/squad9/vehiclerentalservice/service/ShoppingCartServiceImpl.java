package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.response.CarResponseDTO;
import org.squad9.vehiclerentalservice.dto.response.ShoppingCartResponseDTO;
import org.squad9.vehiclerentalservice.exception.RestException;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.repository.CarRepository;
import org.squad9.vehiclerentalservice.repository.ShoppingCartRepository;
import org.squad9.vehiclerentalservice.service.interfaces.ShoppingCartService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ShoppingCartResponseDTO> findAll() {
        List<ShoppingCartModel> shoppingCarts = shoppingCartRepository.findAll();
        List<ShoppingCartResponseDTO> response = new ArrayList<>();

        shoppingCarts.forEach(shoppingCart -> response.add(modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class)));
        return response;
    }

    @Override
    public ShoppingCartResponseDTO findById(UUID id) {
        ShoppingCartModel shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Carrinho não encontrado com o ID: " + id));

        return modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class);
    }

    @Override
    public ShoppingCartResponseDTO findByDriver(String email) {
        ShoppingCartModel shoppingCart = shoppingCartRepository.findByDriverEmail(email)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Carrinho de compras não encontrado para o motorista com o email: " + email));
        return modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class);
    }

    @Override
    public List<CarResponseDTO> findShoppingCartsCars(UUID id) {
        ShoppingCartModel shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Carrinho não encontrado com o ID: " + id));

        List<CarModel> cars = shoppingCart.getCars();
        List<CarResponseDTO> response = new ArrayList<>();

        cars.forEach(car -> response.add(modelMapper.map(car, CarResponseDTO.class)));
        return response;
    }

    @Override
    public List<CarResponseDTO> addCarToShoppingCart(UUID id, UUID idCarro) {
        ShoppingCartModel shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Carrinho não encontrado com o ID: " + id));

        CarModel car = carRepository.findById(idCarro)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Carro não encontrado com o ID: " + idCarro));

        shoppingCart.getCars().add(car);
        shoppingCartRepository.save(shoppingCart);

        List<CarResponseDTO> response = new ArrayList<>();
        shoppingCart.getCars().forEach(c -> response.add(modelMapper.map(c, CarResponseDTO.class)));
        return response;
    }

    @Override
    public List<CarResponseDTO> removeCarFromShoppingCart(UUID id, UUID idCarro) {
        ShoppingCartModel shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Carrinho não encontrado com o ID: " + id));

        CarModel car = carRepository.findById(idCarro)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Carro não encontrado com o ID: " + idCarro));

        shoppingCart.getCars().remove(car);
        shoppingCartRepository.save(shoppingCart);

        List<CarResponseDTO> response = new ArrayList<>();
        shoppingCart.getCars().forEach(c -> response.add(modelMapper.map(c, CarResponseDTO.class)));
        return response;
    }
}

