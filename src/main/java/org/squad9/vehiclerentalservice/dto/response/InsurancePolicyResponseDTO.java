package org.squad9.vehiclerentalservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicyResponseDTO {
    private UUID id;
    private String franchiseValue;
    private boolean thirdPartyProtection;
    private boolean naturalCausesProtection;
    private boolean theftProtection;
    private UUID rentalId;
}
