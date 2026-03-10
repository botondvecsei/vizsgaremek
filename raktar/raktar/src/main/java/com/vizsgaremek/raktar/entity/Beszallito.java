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
}
