package org.squad9.vehiclerentalservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponseDTO {
    private UUID id;
    private String licensePlate;
    private String chassis;
    private String color;
    private String dailyRate;
    private String imageURL;
    private String modelName;
    private UUID modelId;
}
