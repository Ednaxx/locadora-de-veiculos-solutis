package org.squad9.vehiclerentalservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.squad9.vehiclerentalservice.model.util.Sexo;


import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pessoa", discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaModel {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Column(nullable = false, length = 50)
    protected String nome;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    protected LocalDate dataNascimento;

    @Column(nullable = false, unique = true, length = 14)
    protected String cpf;

    @Column(nullable = false)
    protected Sexo sexo;

    @Column(nullable = false, unique = true, length = 30)
    protected String email;
}

