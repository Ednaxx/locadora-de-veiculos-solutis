package org.squad9.vehiclerentalservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicyRequestDTO {
    @Positive private BigDecimal franchiseValue;
    @NotNull private boolean thirdPartyProtection;
    @NotNull private boolean naturalCausesProtection;
    @NotNull private boolean theftProtection;
}
