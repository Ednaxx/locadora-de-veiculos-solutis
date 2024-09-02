package org.squad9.vehiclerentalservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.squad9.vehiclerentalservice.enums.PaymentMethods;

import java.util.UUID;

@Data
@Entity
@Table(name = "pagamentos")
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "rental_id", nullable = false)
    private RentalModel rental;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_de_pagamento", nullable = false)
    private PaymentMethods paymentMethod;

    @Column(nullable = false)
    private boolean confirmed;
}
