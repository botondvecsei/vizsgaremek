package com.vizsgaremek.raktar.service;

import com.vizsgaremek.raktar.entity.*;
import com.vizsgaremek.raktar.entity.enums.RendelesStatusz;
import com.vizsgaremek.raktar.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeszerzesService {

    private final TermekRepository termekRepository;
    private final BeszallitoArRepository beszallitoArRepository;
    private final RendelesRepository rendelesRepository;
    private final RendelesTetelRepository tetelRepository;
    private final FelhasznaloRepository felhasznaloRepository;

    @Transactional
    public void automatikusBeszerzesFuttatasa(Integer rendszerUserId) {
        Felhasznalo rendszerUser = felhasznaloRepository.findById(rendszerUserId)
                .orElseThrow(() -> new RuntimeException("Rendszer felhasználó nem található!"));

        List<Termek> kritikusTermekek = termekRepository.findKritikusKeszlet();

        for (Termek termek : kritikusTermekek){
            int hianyzoMennyiseg = termek.getAjanlottSzint() - termek.getJelenlegiSzint();

            if (hianyzoMennyiseg <= 0) continue;

            Optional<BeszallitoAr> legjobbAjanlatOpt = beszallitoArRepository.findFirstByTermekIdOrderByArAsc(termek.getId());

            if(legjobbAjanlatOpt.isPresent()){
                BeszallitoAr legjobbAjanlat = legjobbAjanlatOpt.get();
                Beszallito beszallito = legjobbAjanlat.getBeszallito();
                BigDecimal egysegAr = legjobbAjanlat.getAr();
                BigDecimal tetelOsszAr = egysegAr.multiply(new BigDecimal(hianyzoMennyiseg));

                Rendeles ujRendeles = new Rendeles();
                ujRendeles.setLetrehozo(rendszerUser);
                ujRendeles.setBeszallito(beszallito);
                ujRendeles.setStatusz(RendelesStatusz.tervezett);
                ujRendeles.setOsszar(tetelOsszAr);
                ujRendeles.setLetrehozasDatum(LocalDateTime.now());

                Rendeles mentettRendeles = rendelesRepository.save(ujRendeles);

                RendelesTetel tetel = new RendelesTetel();
                tetel.setRendeles(mentettRendeles);
                tetel.setTermek(termek);
                tetel.setMennyiseg(hianyzoMennyiseg);
                tetel.setAr(egysegAr);
            }
        }
    }
}
