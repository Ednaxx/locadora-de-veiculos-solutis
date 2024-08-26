package org.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.system.entity.CarrinhoCompra;
import org.system.entity.Motorista;

public interface CarrinhoCompraRepository extends JpaRepository<CarrinhoCompra, Long> {
    CarrinhoCompra findByMotorista(Motorista motorista);
}
