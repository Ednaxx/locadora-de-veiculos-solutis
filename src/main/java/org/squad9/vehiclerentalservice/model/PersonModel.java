package org.squad9.vehiclerentalservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.squad9.vehiclerentalservice.enums.Gender;

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

    @Column(name = "nome", nullable = false, length = 50)
    protected String name;

    @Column(name = "data_nascimento", nullable = false)
    protected LocalDate birthDate;

    @JsonProperty("CPF")
    @Column(nullable = false, unique = true, length = 14)
    protected String CPF;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    protected Gender gender;

    @Column(name = "email", nullable = false, unique = true, length = 30)
    protected String email;
}

