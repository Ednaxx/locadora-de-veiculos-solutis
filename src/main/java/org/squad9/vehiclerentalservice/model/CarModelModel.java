package org.squad9.vehiclerentalservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.squad9.vehiclerentalservice.model.util.Category;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "modelos_carro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "descricao", nullable = false)
    private String description;

    @Column(nullable = false, length = 22)
    private Category category;

    @OneToMany
    @JoinColumn(name = "carro_id")
    private List<CarModel> car;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private ManufacturerModel manufacturer;
}
