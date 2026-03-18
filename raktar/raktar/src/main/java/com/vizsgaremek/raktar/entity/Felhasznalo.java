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

    @Column(nullable = false)
    private String jelszo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Jogkor jogkor;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public Jogkor getJogkor() {
        return jogkor;
    }

    public void setJogkor(Jogkor jogkor) {
        this.jogkor = jogkor;
    }
}
