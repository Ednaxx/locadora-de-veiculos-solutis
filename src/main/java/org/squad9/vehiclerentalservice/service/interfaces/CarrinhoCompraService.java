package org.system.service.interfaces;

import org.system.entity.CarrinhoCompra;
import org.system.entity.Carro;

import java.util.List;

public interface CarrinhoCompraService {
    CarrinhoCompra save(CarrinhoCompra carrinhoCompra);
    List<CarrinhoCompra> findAll();
    CarrinhoCompra findById(Long id);
    void removerCarro(CarrinhoCompra carrinhoCompra, Carro carro);
}
