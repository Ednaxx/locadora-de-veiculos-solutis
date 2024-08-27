package org.squad9.vehiclerentalservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.squad9.vehiclerentalservice.model.CarroModel;
import org.squad9.vehiclerentalservice.model.MotoristaModel;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DetalhesReservaDTO implements Serializable {
    private String dataInicio;
    private String dataDevolucao;
    private CarroModel carro;
    private String emailMotorista;
    private MotoristaModel motorista;

    public DetalhesReservaDTO(String dataInicio, String dataDevolucao, CarroModel carro, String emailMotorista) {
        this.dataInicio = dataInicio;
        this.dataDevolucao = dataDevolucao;
        this.carro = carro;
        this.emailMotorista = emailMotorista;
    }
}
