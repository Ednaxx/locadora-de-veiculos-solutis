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
        List<ShoppingCartModel> cartList = shoppingCartRepository.findAll();
        return cartList.isEmpty() ? Collections.emptyList() : cartList;
    }

    @Override
    public ShoppingCartModel findById(UUID cartId) {
        return shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Carrinho de compras não encontrado com o ID: " + cartId));
    }

    @Override
    public ShoppingCartModel findByDriver(String email) {
        return shoppingCartRepository.findByDriverEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Carrinho de compras não encontrado para o motorista com o email: " + email));
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

