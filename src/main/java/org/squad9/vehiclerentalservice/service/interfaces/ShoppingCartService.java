package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.model.CarModel;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartService {
    ShoppingCartModel save(ShoppingCartModel carrinhoCompra);
    List<ShoppingCartModel> findAll();
    ShoppingCartModel findById(UUID id);
    void removerCarro(ShoppingCartModel carrinhoCompra, CarModel carro);
}
