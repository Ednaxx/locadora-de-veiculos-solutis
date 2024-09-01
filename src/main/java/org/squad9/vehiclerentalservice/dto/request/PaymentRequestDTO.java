package org.squad9.vehiclerentalservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.squad9.vehiclerentalservice.enums.PaymentMethods;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {

    @NotNull
    private UUID rentalId;

    @NotNull
    private PaymentMethods paymentMethod;

    @NotNull
    private long cardNumber;

    @NotNull 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate expirationDate;

    @NotNull
    private String CVV;
}
