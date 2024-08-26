package org.system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@DiscriminatorValue("Motorista")
@NoArgsConstructor
public class Motorista extends Pessoa  {

    @Column(name = "numeroCNH", unique = true)
    private String numeroCNH;
    @OneToOne
    @JoinColumn(name = "carrinho_compra_id")
    private org.system.entity.CarrinhoCompra carrinhoCompra;
    @OneToMany
    private List<org.system.entity.Aluguel> alugueis;

    public Motorista(String nome, LocalDate dataNascimento, String cpf, Sexo sexo, String email, String numeroCNH, org.system.entity.CarrinhoCompra carrinhoCompra) {
        super(nome, dataNascimento, cpf, sexo, email);
        this.numeroCNH = numeroCNH;
        this.carrinhoCompra = carrinhoCompra;
    }
}
