package org.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "apolices_seguro")
@NoArgsConstructor
public class ApoliceSeguro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valorFranquia")
    private BigDecimal valorFranquia;
    @Column(name = "protecaoTerceiro")
    private boolean protecaoTerceiro;
    @Column(name = "protecaoCausasNaturais")
    private boolean protecaoCausasNaturais;
    @Column(name = "protecaoRoubo")
    private boolean protecaoRoubo;
    @OneToOne(mappedBy = "apoliceSeguro")
    @JsonBackReference
    private org.system.entity.Aluguel aluguel;

    public ApoliceSeguro(Long id, BigDecimal valorFranquia, boolean protecaoTerceiro, boolean protecaoCausasNaturais, boolean protecaoRoubo) {
        this.id = id;
        this.valorFranquia = valorFranquia;
        this.protecaoTerceiro = protecaoTerceiro;
        this.protecaoCausasNaturais = protecaoCausasNaturais;
        this.protecaoRoubo = protecaoRoubo;
    }
}
