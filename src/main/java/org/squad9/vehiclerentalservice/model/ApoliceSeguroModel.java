package org.squad9.vehiclerentalservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "apolices_seguro")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApoliceSeguroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private BigDecimal valorFranquia;

    @Column(nullable = false)
    private boolean protecaoTerceiro;

    @Column(nullable = false)
    private boolean protecaoCausasNaturais;

    @Column(nullable = false)
    private boolean protecaoRoubo;

    @OneToOne(mappedBy = "apoliceSeguro")
    @JsonBackReference
    private AluguelModel aluguel;
}
