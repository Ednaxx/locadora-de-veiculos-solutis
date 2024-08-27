package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.CarrinhoCompraModel;
import org.squad9.vehiclerentalservice.model.CarroModel;

import java.util.List;
import java.util.UUID;

public interface CarrinhoCompraService {
    CarrinhoCompraModel save(CarrinhoCompraModel carrinhoCompra);
    List<CarrinhoCompraModel> findAll();
    CarrinhoCompraModel findById(UUID id);
    void removerCarro(CarrinhoCompraModel carrinhoCompra, CarroModel carro);
}
