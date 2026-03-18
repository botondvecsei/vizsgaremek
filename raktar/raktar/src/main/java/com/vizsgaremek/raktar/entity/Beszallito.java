package com.vizsgaremek.raktar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "beszallitok")
public class Beszallito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nev;

    private String cim;
    private String telefonszam;
    private Integer rangsor;

    public Beszallito(Integer id, String nev, String cim, String telefonszam, Integer rangsor) {
        this.id = id;
        this.nev = nev;
        this.cim = cim;
        this.telefonszam = telefonszam;
        this.rangsor = rangsor;
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

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    public Integer getRangsor() {
        return rangsor;
    }

    public void setRangsor(Integer rangsor) {
        this.rangsor = rangsor;
    }
}
