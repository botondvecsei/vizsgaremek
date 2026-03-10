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
}
