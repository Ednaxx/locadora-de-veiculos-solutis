package org.squad9.vehiclerentalservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carrinho_compras")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingCartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "motorista_id")
    private DriverModel driver;

    @ManyToMany
    @JoinTable(
            name = "carrinho_compra_carro",
            joinColumns = @JoinColumn(name = "carrinho_id"),
            inverseJoinColumns = @JoinColumn(name = "carro_id")
    )
    private List<CarModel> carList;
}
