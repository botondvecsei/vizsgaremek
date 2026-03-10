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
}