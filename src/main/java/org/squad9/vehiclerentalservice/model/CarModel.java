package org.squad9.vehiclerentalservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carros")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "placa", nullable = false, unique = true, length = 7)
    private String licensePlate;

    @Column(name = "chassi", nullable = false, unique = true, length = 20)
    private String chassi;

    @Column(name = "cor", nullable = false, length = 20)
    private String color;

    @Column(name = "valor_diaria", nullable = false)
    private BigDecimal dailyRate;

    @ManyToMany
    @JoinTable(
            name = "carro_acessorio",
            joinColumns = @JoinColumn(name = "carro_id"),
            inverseJoinColumns = @JoinColumn(name = "acessorio_id")
    )
    private List<AccessoryModel> accessories;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private CarModelModel carModel;

    @OneToMany
    private List<RentalModel> rent;

    @ElementCollection
    @CollectionTable(name = "carro_datas_ocupadas", joinColumns = @JoinColumn(name = "carro_id"))
    @Column(name = "data_ocupada")
    private List<LocalDate> occupiedDates = new ArrayList<>();

    @Column(nullable = false)
    private String urlImage;

    public boolean isDisponivelParaAluguel(LocalDate dataInicio, LocalDate dataDevolucao) {
        for (LocalDate data : occupiedDates) {
            if (!data.isBefore(dataInicio) && !data.isAfter(dataDevolucao)) return false;
        }

        return true;
    }

    public void bloquearDatas(LocalDate dataInicio, LocalDate dataDevolucao) {
        LocalDate data = dataInicio;

        while (!data.isAfter(dataDevolucao)) {
            occupiedDates.add(data);
            data = data.plusDays(1);
        }
    }
}
