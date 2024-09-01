package org.squad9.vehiclerentalservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("motorista")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverModel extends PersonModel {
    @JsonProperty("CNH")
    @Column(nullable = false, unique = true, length = 10)
    private String CNH;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "carrinho_compra_id")
    private ShoppingCartModel shoppingCart;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @JsonManagedReference("driverReference")
    private List<RentalModel> rents;
}
