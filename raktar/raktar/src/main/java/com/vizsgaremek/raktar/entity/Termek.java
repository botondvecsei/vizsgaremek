package com.vizsgaremek.raktar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "termekek")
public class Termek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nev;

    @Column(name = "jelenlegi_szint")
    private Integer jelenlegiSzint;

    @Column(name = "min_szint")
    private Integer minSzint;

    @Column(name = "ajanlott_szint")
    private Integer ajanlottSzint;

    public Termek(Integer id, String nev, Integer jelenlegiSzint, Integer minSzint, Integer ajanlottSzint) {
        this.id = id;
        this.nev = nev;
        this.jelenlegiSzint = jelenlegiSzint;
        this.minSzint = minSzint;
        this.ajanlottSzint = ajanlottSzint;
    }

    public Termek() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Integer getJelenlegiSzint() {
        return jelenlegiSzint;
    }

    public void setJelenlegiSzint(Integer jelenlegiSzint) {
        this.jelenlegiSzint = jelenlegiSzint;
    }

    public Integer getMinSzint() {
        return minSzint;
    }

    public void setMinSzint(Integer minSzint) {
        this.minSzint = minSzint;
    }

    public Integer getAjanlottSzint() {
        return ajanlottSzint;
    }

    public void setAjanlottSzint(Integer ajanlottSzint) {
        this.ajanlottSzint = ajanlottSzint;
    }
}
