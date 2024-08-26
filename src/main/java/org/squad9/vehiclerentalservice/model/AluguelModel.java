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
public class AluguelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate dataPedido;

    @Column(nullable = false)
    private LocalDate dataEntrega;

    @Column(nullable = false)
    private LocalDate dataDevolucao;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @OneToOne
    @JoinColumn(name = "apolice_seguro_id", nullable = false)
    private ApoliceSeguroModel apoliceSeguro;

    @ManyToOne
    @JoinColumn(name = "carro_id", nullable = false)
    private CarroModel carro;

    @ManyToOne
    @JoinColumn(name = "motorista_id", nullable = false)
    private MotoristaModel motorista;
}
