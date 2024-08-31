package org.squad9.vehiclerentalservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "acessorios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descricao", length = 500, nullable = false)
    private String description;
}
