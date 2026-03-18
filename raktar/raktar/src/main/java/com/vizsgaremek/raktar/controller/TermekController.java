package com.vizsgaremek.raktar.controller;

import com.vizsgaremek.raktar.entity.Termek;
import com.vizsgaremek.raktar.service.TermekService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/termek")
@RequiredArgsConstructor
public class TermekController {
    @Autowired
    private TermekService termekService;

    @GetMapping
    public List<Termek> getOsszesTermek() {
        return termekService.getOsszesTermek();
    }

    @GetMapping("/{id}")
    public Termek getTermekById(@PathVariable Integer id){
        return termekService.getTermekById(id);
    }

    @PostMapping
    public Termek termekLetrehozas(@RequestBody Termek termek){
            return termekService.termekMentes(termek);
    }
    @DeleteMapping("/{id}")
    public void termekTorles(@PathVariable Integer id){
        termekService.termekTorles(id);
    }
}
