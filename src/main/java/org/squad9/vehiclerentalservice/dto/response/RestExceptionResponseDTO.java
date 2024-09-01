package org.squad9.vehiclerentalservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestExceptionResponseDTO {

    private Date timestamp;
    private Integer status;
    private String error;
    private String message;

}
