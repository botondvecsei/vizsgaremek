package com.vizsgaremek.raktar.service;

import com.vizsgaremek.raktar.entity.Termek;
import com.vizsgaremek.raktar.repository.TermekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermekService {
    @Autowired
    private TermekRepository termekRepository;

    public List<Termek> getOsszesTermek(){
        return termekRepository.findAll();
    }

    public Termek termekMentes(Termek termek){
        return termekRepository.save(termek);
    }

    public Termek getTermekById(Integer id){
        return  termekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("A termék nem található ezzel az ID-val!" + id));
    }

    public void termekTorles(Integer id){
        if (!termekRepository.existsById(id)){
            throw new RuntimeException("Nem törölhető, mert a termék nem létezik");
        }
        termekRepository.deleteById(id);
    }
}
