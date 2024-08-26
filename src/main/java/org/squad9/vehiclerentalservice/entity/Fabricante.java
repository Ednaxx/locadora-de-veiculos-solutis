package org.system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "fabricantes")
@NoArgsConstructor
public class Fabricante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "fabricante")
    private List<ModeloCarro> modelosCarro;

    public Fabricante(Long id, String nome, List<ModeloCarro> modelosCarro) {
        this.id = id;
        this.nome = nome;
        this.modelosCarro = modelosCarro;
    }
}
