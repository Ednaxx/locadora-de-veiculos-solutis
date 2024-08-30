package org.squad9.vehiclerentalservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel extends PersonModel {
    @Column(name = "matricula", unique = true)
    private String registration;
}
