package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.repository.ShoppingCartRepository;
import org.squad9.vehiclerentalservice.service.interfaces.ShoppingCartService;

import java.util.*;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCartModel> findAll() {
        List<ShoppingCartModel> carrinho = shoppingCartRepository.findAll();
        if (carrinho.isEmpty()) return null;

        return carrinho;
    }

    @Override
    public ShoppingCartModel findById(UUID carrinhoId) {
        try{
            Optional<ShoppingCartModel> carrinhoOptional = shoppingCartRepository.findById(carrinhoId);
            if (carrinhoOptional.isPresent()){
                return carrinhoOptional.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public ShoppingCartModel findByDriver(String email) {
        // TODO: implement this
    }

    @Override
    public ShoppingCartModel update(UUID id, ShoppingCartModel shoppingCart) {
        try {
            if (!shoppingCartRepository.existsById(id)) {
                throw new IllegalArgumentException("Carrinho de compras não encontrado.");
            }

            if (shoppingCart.getDriver() == null) {
                throw new IllegalArgumentException("Carrinho de compras deve estar associado a um motorista.");
            }

            shoppingCart.setId(id);
            return shoppingCartRepository.save(shoppingCart);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar carrinho de compras: " + e.getMessage());
        }
    }
}
