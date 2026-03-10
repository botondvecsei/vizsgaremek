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
}