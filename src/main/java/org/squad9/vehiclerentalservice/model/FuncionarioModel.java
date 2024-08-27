package org.squad9.vehiclerentalservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioModel extends PessoaModel {
    @Column(name = "matricula", unique = true)
    private String matricula;
}
