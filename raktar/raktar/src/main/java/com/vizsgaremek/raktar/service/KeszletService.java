package com.vizsgaremek.raktar.service;


import com.vizsgaremek.raktar.entity.Keszletmozgas;
import com.vizsgaremek.raktar.entity.Termek;
import com.vizsgaremek.raktar.entity.enums.MozgasTipus;
import com.vizsgaremek.raktar.repository.KeszletmozgasRepository;
import com.vizsgaremek.raktar.repository.TermekRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class KeszletService {
    @Autowired
    private TermekRepository termekRepository;
    @Autowired
    private KeszletmozgasRepository mozgasRepository;

    @Transactional
    public void keszletModositas(Integer termekId, Integer valtozasMennyiseg, MozgasTipus tipus, String megjegyzes){

        Termek termek = termekRepository.findById(termekId)
                .orElseThrow(() -> new RuntimeException("Termék nem található: " + termekId));

        int ujKeszlet = termek.getJelenlegiSzint();
        if (tipus == MozgasTipus.bevetel){
            ujKeszlet += valtozasMennyiseg;
        } else if (tipus == MozgasTipus.kiadas) {
            if (ujKeszlet < valtozasMennyiseg){
                throw new RuntimeException("Nincs elég készlet a kiadáshoz!");
            }
            ujKeszlet -= valtozasMennyiseg;
        } else {
            ujKeszlet += valtozasMennyiseg;
        }

        termek.setJelenlegiSzint(ujKeszlet);
        termekRepository.save(termek);

        Keszletmozgas mozgas = new Keszletmozgas();
        mozgas.setTermek(termek);
        mozgas.setTipus(tipus);
        mozgas.setMennyiseg(valtozasMennyiseg);
        mozgas.setMegjegyzes(megjegyzes);
        mozgasRepository.save(mozgas);
    }
}
