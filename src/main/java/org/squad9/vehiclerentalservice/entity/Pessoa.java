package org.system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "pessoas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pessoa", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public class Pessoa {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "nome")
    protected String nome;
    @Column(name = "dataNascimento")
    @Temporal(TemporalType.DATE)
    protected LocalDate dataNascimento;
    @Column(name = "cpf", unique = true)
    protected String cpf;
    @Column(name = "sexo")
    protected Sexo sexo;
    @Column(name = "email", unique = true)
    protected String email;

    public Pessoa(String nome, LocalDate dataNascimento, String cpf, Sexo sexo, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
    }
}

