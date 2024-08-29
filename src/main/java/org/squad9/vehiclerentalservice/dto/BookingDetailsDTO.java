package org.squad9.vehiclerentalservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.DriverModel;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BookingDetailsDTO implements Serializable {
    private String dataInicio;
    private String dataDevolucao;
    private CarModel carro;
    private String emailMotorista;
    private DriverModel motorista;

    public BookingDetailsDTO(String dataInicio, String dataDevolucao, CarModel carro, String emailMotorista) {
        this.dataInicio = dataInicio;
        this.dataDevolucao = dataDevolucao;
        this.carro = carro;
        this.emailMotorista = emailMotorista;
    }
}
