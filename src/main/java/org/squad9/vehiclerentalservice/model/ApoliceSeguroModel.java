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

    @Column(name = "valor_franquia", nullable = false)
    private BigDecimal franchiseValue;

    @Column(name = "protecao_terceiro", nullable = false)
    private boolean thirdPartyProtection;

    @Column(name = "protecao_causas_naturais", nullable = false)
    private boolean naturalCausesProtection;

    @Column(name = "protecao_roubo", nullable = false)
    private boolean theftProtection;

    @OneToOne(mappedBy = "apoliceSeguro")
    @JsonBackReference
    private AluguelModel rent;
}
