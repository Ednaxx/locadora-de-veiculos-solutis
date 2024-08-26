package org.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "carrinhoCompras")
@NoArgsConstructor
public class CarrinhoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;
    @ManyToMany
    @JoinTable(
            name = "carrinho_compra_carro",
            joinColumns = @JoinColumn(name = "carrinho_id"),
            inverseJoinColumns = @JoinColumn(name = "carro_id")
    )
    private List<org.system.entity.Carro> listaCarros = new ArrayList<>();

    public CarrinhoCompra(Long id, Motorista motorista, List<org.system.entity.Carro> listaCarros) {
        this.id = id;
        this.motorista = motorista;
        this.listaCarros = listaCarros;
    }
}
