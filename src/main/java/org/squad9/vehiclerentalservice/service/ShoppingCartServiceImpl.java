package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.response.ShoppingCartResponseDTO;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.repository.ShoppingCartRepository;
import org.squad9.vehiclerentalservice.service.interfaces.ShoppingCartService;

import java.util.*;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
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
                .orElseThrow(() -> new IllegalArgumentException("Carrinho não encontrado com o ID: " + id));

        return modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class);
    }

    @Override
    public ShoppingCartResponseDTO findByDriver(String email) {
        ShoppingCartModel shoppingCart = shoppingCartRepository.findByDriverEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Carrinho de compras não encontrado para o motorista com o email: " + email));
        return modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class);
    }
}

