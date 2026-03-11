package com.vizsgaremek.raktar.service;

import com.vizsgaremek.raktar.entity.Felhasznalo;
import com.vizsgaremek.raktar.repository.FelhasznaloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final FelhasznaloRepository felhasznaloRepository;

    public Felhasznalo bejelentkezes(String email, String jelszo) {
        Felhasznalo felhasznalo = felhasznaloRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Hibás email cím vagy felhasználó nem létezik!"));

        if (!felhasznalo.getJelszo().equals(jelszo)) {
            throw new RuntimeException("Hibás jelszó!");
        }

        // Ha minden oké, visszaadjuk a felhasználó adatait
        return felhasznalo;
    }
}