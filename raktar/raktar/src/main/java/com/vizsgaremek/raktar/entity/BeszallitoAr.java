package com.vizsgaremek.raktar.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "beszallito_arak")
public class BeszallitoAr {

    @EmbeddedId
    private BeszallitoArId id = new BeszallitoArId();

    @ManyToOne
    @MapsId("beszallitoId")
    @JoinColumn(name = "beszallito_id")
    private Beszallito beszallito;

    @ManyToOne
    @MapsId("termekId")
    @JoinColumn(name = "termek_id")
    private Termek termek;

    @Column(nullable = false)
    private BigDecimal ar;
}