package org.squad9.vehiclerentalservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "alugueis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "data_pedido",nullable = false)
    private LocalDate orderDate;

    @Column(name = "data_entrega", nullable = false)
    private LocalDate deliveryDate;

    @Column(name = "data_devolucao",nullable = false)
    private LocalDate returnDate;

    @Column(name = "valor_total",nullable = false)
    private BigDecimal totalValue;

    @OneToOne
    @JoinColumn(name = "apolice_seguro_id", nullable = false)
    private InsurancePolicyModel insurancePolicy;

    @ManyToOne
    @JoinColumn(name = "carro_id", nullable = false)
    private CarModel car;

    @ManyToOne
    @JoinColumn(name = "motorista_id", nullable = false)
    private DriverModel driver;
}
