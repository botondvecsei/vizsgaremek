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

    public BeszallitoArId getId() {
        return id;
    }

    public void setId(BeszallitoArId id) {
        this.id = id;
    }

    public Beszallito getBeszallito() {
        return beszallito;
    }

    public void setBeszallito(Beszallito beszallito) {
        this.beszallito = beszallito;
    }

    public Termek getTermek() {
        return termek;
    }

    public void setTermek(Termek termek) {
        this.termek = termek;
    }

    public BigDecimal getAr() {
        return ar;
    }

    public void setAr(BigDecimal ar) {
        this.ar = ar;
    }
}