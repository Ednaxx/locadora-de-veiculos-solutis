package org.squad9.vehiclerentalservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessoryResponseDTO {
    private UUID id;
    private String name;
    private String description;
}