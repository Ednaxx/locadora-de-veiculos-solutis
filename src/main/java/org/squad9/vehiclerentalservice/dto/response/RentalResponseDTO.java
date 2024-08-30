package org.squad9.vehiclerentalservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalResponseDTO {
    private UUID id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private LocalDate returnDate;
    private BigDecimal totalValue;
    private UUID insurancePolicyId;
    private UUID carId;
    private UUID driverId;
}
