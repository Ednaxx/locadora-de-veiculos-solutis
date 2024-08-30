package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.model.CarModel;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartService {
    List<ShoppingCartModel> findAll();
    ShoppingCartModel findById(UUID id);
    ShoppingCartModel save(ShoppingCartModel shoppingCart);
    void remove(UUID id);
    ShoppingCartModel update(UUID id, ShoppingCartModel shoppingCart);
}
