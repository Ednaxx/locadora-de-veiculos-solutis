package org.squad9.vehiclerentalservice.handler;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.squad9.vehiclerentalservice.dto.response.RestExceptionResponseDTO;
import org.squad9.vehiclerentalservice.exception.RestException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<RestExceptionResponseDTO> handleException(RestException error) {
        RestExceptionResponseDTO response = new RestExceptionResponseDTO(
                new Date(),
                error.getStatusCode().value(),
                error.getStatusCode().getReasonPhrase(),
                error.getMessage()
        );
        return ResponseEntity.status(error.getStatusCode()).body(response);
    }
}
