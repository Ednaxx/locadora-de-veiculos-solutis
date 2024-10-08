package org.squad9.vehiclerentalservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarTypeRequestDTO {
    @Size(max = 500) private String description;
    @NotBlank @Size(max = 18) private String category;
    @NotNull private UUID manufacturerId;
}
