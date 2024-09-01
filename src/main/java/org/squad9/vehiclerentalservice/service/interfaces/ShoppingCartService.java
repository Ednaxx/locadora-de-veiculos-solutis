package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.dto.response.CarResponseDTO;
import org.squad9.vehiclerentalservice.dto.response.ShoppingCartResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartService {
    List<ShoppingCartResponseDTO> findAll();

    ShoppingCartResponseDTO findById(UUID id);

    ShoppingCartResponseDTO findByDriverEmail(String email);

    List<CarResponseDTO> findShoppingCartsCars(UUID id);

    List<CarResponseDTO> addCarToShoppingCart(UUID id, UUID idCarro);

    List<CarResponseDTO> removeCarFromShoppingCart(UUID id, UUID idCarro);
}
