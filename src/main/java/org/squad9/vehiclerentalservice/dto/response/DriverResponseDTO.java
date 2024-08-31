package org.squad9.vehiclerentalservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverResponseDTO {
    private UUID id;
    private String name;
    private String birthDate;
    private String CPF;
    private String CNH;
    private String email;
    private String gender;
}
