package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.dto.response.ShoppingCartResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartService {
    List<ShoppingCartResponseDTO> findAll();
    ShoppingCartResponseDTO findById(UUID id);
    ShoppingCartResponseDTO findByDriver(String email);
}
