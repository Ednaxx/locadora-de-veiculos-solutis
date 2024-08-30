package org.squad9.vehiclerentalservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddCartDTO implements Serializable {
    String email;
    UUID id;

    public AddCartDTO(String email, UUID id) {
        this.email = email;
        this.id = id;
    }
}
