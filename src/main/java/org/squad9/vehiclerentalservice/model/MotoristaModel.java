package org.squad9.vehiclerentalservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("Motorista")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotoristaModel extends PessoaModel {
    @Column(name = "numero_CNH", nullable = false, unique = true, length = 11)
    private String CNH_number;

    @OneToOne
    @JoinColumn(name = "carrinho_compra_id")
    private CarrinhoCompraModel shoppingCart;

    @OneToMany
    private List<AluguelModel> rent;
}
