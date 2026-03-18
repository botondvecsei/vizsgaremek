package com.vizsgaremek.raktar.entity;

import com.vizsgaremek.raktar.entity.enums.RendelesStatusz;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rendelesek")
public class Rendeles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Felhasznalo letrehozo;

    @ManyToOne
    @JoinColumn(name = "jovahagyo_id")
    private Felhasznalo jovahagyo;

    @ManyToOne
    @JoinColumn(name = "beszallito_id")
    private Beszallito beszallito;

    @Enumerated(EnumType.STRING)
    private RendelesStatusz statusz;

    private BigDecimal osszar;

    @Column(name = "letrehozas_datum", insertable = false, updatable = false)
    private LocalDateTime letrehozasDatum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Felhasznalo getLetrehozo() {
        return letrehozo;
    }

    public void setLetrehozo(Felhasznalo letrehozo) {
        this.letrehozo = letrehozo;
    }

    public Felhasznalo getJovahagyo() {
        return jovahagyo;
    }

    public void setJovahagyo(Felhasznalo jovahagyo) {
        this.jovahagyo = jovahagyo;
    }

    public Beszallito getBeszallito() {
        return beszallito;
    }

    public void setBeszallito(Beszallito beszallito) {
        this.beszallito = beszallito;
    }

    public RendelesStatusz getStatusz() {
        return statusz;
    }

    public void setStatusz(RendelesStatusz statusz) {
        this.statusz = statusz;
    }

    public BigDecimal getOsszar() {
        return osszar;
    }

    public void setOsszar(BigDecimal osszar) {
        this.osszar = osszar;
    }

    public LocalDateTime getLetrehozasDatum() {
        return letrehozasDatum;
    }

    public void setLetrehozasDatum(LocalDateTime letrehozasDatum) {
        this.letrehozasDatum = letrehozasDatum;
    }
}