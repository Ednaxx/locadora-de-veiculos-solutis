package org.squad9.vehiclerentalservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "fabricantes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "fabricante")
    private List<CarModelModel> carModel;
}
