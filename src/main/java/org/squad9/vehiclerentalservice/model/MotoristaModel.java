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
    @Column(nullable = false, unique = true, length = 11)
    private String numeroCNH;

    @OneToOne
    @JoinColumn(name = "carrinho_compra_id")
    private CarrinhoCompraModel carrinhoCompra;

    @OneToMany
    private List<AluguelModel> alugueis;
}
