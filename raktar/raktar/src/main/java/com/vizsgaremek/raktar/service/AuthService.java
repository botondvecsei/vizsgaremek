package com.vizsgaremek.raktar.service;

import com.vizsgaremek.raktar.entity.Felhasznalo;
import com.vizsgaremek.raktar.repository.FelhasznaloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    public Felhasznalo bejelentkezes(String email, String jelszo) {
        Felhasznalo felhasznalo = felhasznaloRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Hibás email cím vagy felhasználó nem létezik!"));

        if (!felhasznalo.getJelszo().equals(jelszo)) {
            throw new RuntimeException("Hibás jelszó!");
        }

        return felhasznalo;
    }
}