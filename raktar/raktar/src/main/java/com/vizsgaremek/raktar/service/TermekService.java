package com.vizsgaremek.raktar.service;

import com.vizsgaremek.raktar.entity.Termek;
import com.vizsgaremek.raktar.repository.TermekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TermekService {

    private final TermekRepository termekRepository;

    public List<Termek> findAll() { return termekRepository.findAll(); }
    public Optional<Termek> findById(Integer id) { return termekRepository.findById(id); }

    @Transactional
    public Termek save(Termek termek) { return termekRepository.save(termek); }

    @Transactional
    public Termek update(Integer id, Termek friss) {
        Termek regi = termekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nincs ilyen termék!"));

        regi.setNev(friss.getNev());
        regi.setMinSzint(friss.getMinSzint());
        regi.setAjanlottSzint(friss.getAjanlottSzint());

        return termekRepository.save(regi);
    }

    @Transactional
    public void delete(Integer id) { termekRepository.deleteById(id); }
}
