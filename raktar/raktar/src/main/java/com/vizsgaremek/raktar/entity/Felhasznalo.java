package com.vizsgaremek.raktar.entity;

import com.vizsgaremek.raktar.entity.enums.Jogkor;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "felhasznalok")
public class Felhasznalo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nev;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Jogkor jogkor;
}
