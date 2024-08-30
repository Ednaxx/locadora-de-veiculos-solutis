package org.squad9.vehiclerentalservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column
    private String urlImage;

    @ManyToMany
    @JoinTable(
            name = "carro_acessorios",
            joinColumns = @JoinColumn(name = "carro_id"),
            inverseJoinColumns = @JoinColumn(name = "acessorio_id")
    )
    private List<AccessoryModel> accessories;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    @JsonBackReference("carModelReference")
    private CarModelModel carModel;

    @OneToMany(mappedBy = "car")
    @JsonManagedReference("carReference")
    private List<RentalModel> rents;

    @ElementCollection
    @CollectionTable(name = "carro_datas_ocupadas", joinColumns = @JoinColumn(name = "carro_id"))
    @Column(name = "data_ocupada")
    private List<LocalDate> occupiedDates;

    public boolean isAvailableToRent(LocalDate startingDate, LocalDate returnDate) {
        for (LocalDate data : occupiedDates)
            if (!data.isBefore(startingDate) && !data.isAfter(returnDate)) return false;

        return true;
    }

    public void blockDates(LocalDate startingDate, LocalDate returnDate) {
        LocalDate data = startingDate;

        while (!data.isAfter(returnDate)) {
            occupiedDates.add(data);
            data = data.plusDays(1);
        }
    }
}