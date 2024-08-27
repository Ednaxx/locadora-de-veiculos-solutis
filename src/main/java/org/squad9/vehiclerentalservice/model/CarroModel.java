package org.squad9.vehiclerentalservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carros")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 7)
    private String placa;

    @Column(nullable = false, unique = true, length = 20)
    private String chassi;

    @Column(nullable = false, length = 20)
    private String cor;

    @Column(nullable = false)
    private BigDecimal valorDiaria;

    @ManyToMany
    @JoinTable(
            name = "carro_acessorio",
            joinColumns = @JoinColumn(name = "carro_id"),
            inverseJoinColumns = @JoinColumn(name = "acessorio_id")
    )
    private List<AcessorioModel> acessorios;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private ModeloCarroModel modeloCarro;

    @OneToMany
    private List<AluguelModel> alugueis;

    @ElementCollection
    @CollectionTable(name = "carro_datas_ocupadas", joinColumns = @JoinColumn(name = "carro_id"))
    @Column(name = "data_ocupada")
    private List<LocalDate> datasOcupadas;

    @Column(nullable = false)
    private String urlImagem;

    public boolean isDisponivelParaAluguel(LocalDate dataInicio, LocalDate dataDevolucao) {
        for (LocalDate data : datasOcupadas) {
            if (!data.isBefore(dataInicio) && !data.isAfter(dataDevolucao)) return false;
        }

        return true;
    }

    public void bloquearDatas(LocalDate dataInicio, LocalDate dataDevolucao) {
        LocalDate data = dataInicio;

        while (!data.isAfter(dataDevolucao)) {
            datasOcupadas.add(data);
            data = data.plusDays(1);
        }
    }
}