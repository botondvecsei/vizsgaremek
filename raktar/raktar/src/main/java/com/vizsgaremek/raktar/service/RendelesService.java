package com.vizsgaremek.raktar.service;

import com.vizsgaremek.raktar.entity.Felhasznalo;
import com.vizsgaremek.raktar.entity.Rendeles;
import com.vizsgaremek.raktar.entity.RendelesTetel;
import com.vizsgaremek.raktar.entity.enums.Jogkor;
import com.vizsgaremek.raktar.entity.enums.MozgasTipus;
import com.vizsgaremek.raktar.entity.enums.RendelesStatusz;
import com.vizsgaremek.raktar.repository.FelhasznaloRepository;
import com.vizsgaremek.raktar.repository.KeszletmozgasRepository;
import com.vizsgaremek.raktar.repository.RendelesRepository;
import com.vizsgaremek.raktar.repository.RendelesTetelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RendelesService {
    @Autowired
    private RendelesRepository rendelesRepository;
    @Autowired
    private RendelesTetelRepository tetelRepository;
    @Autowired
    private KeszletService keszletService;
    @Autowired
    private KeszletmozgasRepository keszletmozgasRepository;
    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @Transactional
    public void statuszModositas(Integer rendelesId, RendelesStatusz ujStatusz, Integer vegrehajtoUserId){

        Felhasznalo vegrehajto = felhasznaloRepository.findById(vegrehajtoUserId)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található"));

        if ((ujStatusz == RendelesStatusz.megrendelve || ujStatusz == RendelesStatusz.elutasitva)
                && vegrehajto.getJogkor() != Jogkor.admin){
            throw new RuntimeException("Nincs jogosultságod a rendelés jóváhagyásához");
        }

        Rendeles rendeles = rendelesRepository.findById(rendelesId)
                .orElseThrow(() -> new RuntimeException("Rendelés nem található!"));

        if (rendeles.getStatusz() == ujStatusz) return;

        rendeles.setStatusz(ujStatusz);

        if (ujStatusz == RendelesStatusz.teljesitve){

            List<RendelesTetel> tetelek = tetelRepository.findByRendelesId(rendelesId);

            for (RendelesTetel tetel : tetelek){
                keszletService.keszletModositas(
                        tetel.getTermek().getId(),
                        tetel.getMennyiseg(),
                        MozgasTipus.bevetel,
                        "Automatikus bevételezés" + rendelesId + "számú rendelésből."
                );
            }
        }
        rendelesRepository.save(rendeles);
    }
}
