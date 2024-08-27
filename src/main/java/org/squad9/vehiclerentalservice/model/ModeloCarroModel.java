package org.squad9.vehiclerentalservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.squad9.vehiclerentalservice.model.util.Categoria;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "modelos_carro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloCarroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String descricao;

    @OneToMany
    private List<CarroModel> carros;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    @JsonBackReference
    private FabricanteModel fabricante;

    @Column(nullable = false, length = 22)
    private Categoria categoria;
}
