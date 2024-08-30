package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.ShoppingCartModel;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartService {
    List<ShoppingCartModel> findAll();
    ShoppingCartModel findById(UUID id);
    ShoppingCartModel findByDriver(String email);
    ShoppingCartModel update(UUID id, ShoppingCartModel shoppingCart);
}
