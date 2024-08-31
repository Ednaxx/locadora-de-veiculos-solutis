package org.squad9.vehiclerentalservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequestDTO {
    @NotBlank @Size(max = 50) private String name;
    @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East") LocalDate birthday;
    @NotBlank @Email @Size(max = 30) private String email;
    @NotBlank @Size(min = 14, max = 14) private String CPF;
    @NotBlank @Size(min = 10, max = 10) private String CNH;
    @NotBlank private String gender;
}
