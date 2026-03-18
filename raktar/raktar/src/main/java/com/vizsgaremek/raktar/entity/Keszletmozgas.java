package com.vizsgaremek.raktar.entity;

import com.vizsgaremek.raktar.entity.enums.MozgasTipus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "keszletmozgasok")
public class Keszletmozgas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "termek_id", nullable = false)
    private Termek termek;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MozgasTipus tipus;

    @Column(nullable = false)
    private Integer mennyiseg;

    @Column(insertable = false, updatable = false)
    private LocalDateTime datum;

    private String megjegyzes;

    public Keszletmozgas(Integer id, Termek termek, MozgasTipus tipus, Integer mennyiseg, LocalDateTime datum, String megjegyzes) {
        this.id = id;
        this.termek = termek;
        this.tipus = tipus;
        this.mennyiseg = mennyiseg;
        this.datum = datum;
        this.megjegyzes = megjegyzes;
    }

    public Keszletmozgas() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Termek getTermek() {
        return termek;
    }

    public void setTermek(Termek termek) {
        this.termek = termek;
    }

    public MozgasTipus getTipus() {
        return tipus;
    }

    public void setTipus(MozgasTipus tipus) {
        this.tipus = tipus;
    }

    public Integer getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(Integer mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public String getMegjegyzes() {
        return megjegyzes;
    }

    public void setMegjegyzes(String megjegyzes) {
        this.megjegyzes = megjegyzes;
    }
}