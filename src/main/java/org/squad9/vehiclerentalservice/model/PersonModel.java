package org.squad9.vehiclerentalservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.squad9.vehiclerentalservice.model.util.Gender;


import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pessoa", discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonModel {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Column(name = "nome",nullable = false, length = 50)
    protected String name;

    @Column(name = "data_nascimento",nullable = false)
    @Temporal(TemporalType.DATE)
    protected LocalDate dateOfBirth;

    @Column(name = "cpf",nullable = false, unique = true, length = 14)
    protected String cpf;

    @Column(name = "sexo",nullable = false)
    protected Gender gender;

    @Column(name = "email",nullable = false, unique = true, length = 30)
    protected String email;
}

