package org.squad9.vehiclerentalservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AdicionarCarrinhoDTO implements Serializable {
    String email;
    UUID id;

    public AdicionarCarrinhoDTO(String email, UUID id) {
        this.email = email;
        this.id = id;
    }
}
