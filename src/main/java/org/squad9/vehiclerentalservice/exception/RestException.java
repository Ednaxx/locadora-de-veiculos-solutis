package org.squad9.vehiclerentalservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestException extends RuntimeException {
    private final HttpStatus statusCode;

    public RestException(HttpStatus httpStatus, String errorMessage){
        super(errorMessage);
        this.statusCode = httpStatus;
    }
}
