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
}