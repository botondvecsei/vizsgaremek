package com.vizsgaremek.raktar.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "rendeles_tetelek")
public class RendelesTetel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rendeles_id", nullable = false)
    private Rendeles rendeles;

    @ManyToOne
    @JoinColumn(name = "termek_id", nullable = false)
    private Termek termek;

    @Column(nullable = false)
    private Integer mennyiseg;

    @Column(nullable = false)
    private BigDecimal ar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rendeles getRendeles() {
        return rendeles;
    }

    public void setRendeles(Rendeles rendeles) {
        this.rendeles = rendeles;
    }

    public Termek getTermek() {
        return termek;
    }

    public void setTermek(Termek termek) {
        this.termek = termek;
    }

    public Integer getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(Integer mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public BigDecimal getAr() {
        return ar;
    }

    public void setAr(BigDecimal ar) {
        this.ar = ar;
    }
}