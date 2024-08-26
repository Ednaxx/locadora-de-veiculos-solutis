package org.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;

@Entity
@Table(name = "alugueis")
@Data
@NoArgsConstructor
public class Aluguel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dataPedido")
    private LocalDate dataPedido;
    @Column(name = "dataEntrega")
    private LocalDate dataEntrega;
    @Column(name = "dataDevolucao")
    private LocalDate dataDevolucao;
    @Column(name = "valorTotal")
    private BigDecimal valorTotal;
    @OneToOne
    @JoinColumn(name = "apolice_seguro_id")
    private ApoliceSeguro apoliceSeguro;
    @ManyToOne
    @JoinColumn(name = "carro_id")
    private org.system.entity.Carro carro;
    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    public Aluguel(LocalDate dataEntrega, LocalDate dataDevolucao, BigDecimal valorTotal, ApoliceSeguro apoliceSeguro) {
        this.dataEntrega = dataEntrega;
        this.dataDevolucao = dataDevolucao;
        this.valorTotal = valorTotal;
        this.apoliceSeguro = apoliceSeguro;
    }
}
