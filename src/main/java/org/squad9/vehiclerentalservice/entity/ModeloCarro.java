package org.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "modelos_carro")
@NoArgsConstructor
public class ModeloCarro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany
    private List<org.system.entity.Carro> carros;
    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    @JsonBackReference
    private org.system.entity.Fabricante fabricante;
    @Column(name = "categoria")
    private org.system.entity.Categoria categoria;

    public ModeloCarro(Long id, String descricao, List<org.system.entity.Carro> carros, org.system.entity.Fabricante fabricante, org.system.entity.Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.carros = carros;
        this.fabricante = fabricante;
        this.categoria = categoria;
    }
}
