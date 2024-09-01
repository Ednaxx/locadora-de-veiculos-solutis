package org.squad9.vehiclerentalservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequestDTO {
    @NotBlank @Size(min = 7, max = 7) private String licensePlate;
    @NotBlank @Size(min = 20, max = 20) private String chassis;
    @NotBlank @Size(max = 20) private String color;
    @Positive private BigDecimal dailyRate;
    private String imageURL;
    @NotBlank private UUID carModelId;
}
