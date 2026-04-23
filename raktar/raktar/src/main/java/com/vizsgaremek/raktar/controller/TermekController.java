package com.vizsgaremek.raktar.controller;

import com.vizsgaremek.raktar.entity.Termek;
import com.vizsgaremek.raktar.service.TermekService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/termek")
@RequiredArgsConstructor
public class TermekController {
    private final TermekService termekService;

    @GetMapping
    public List<Termek> getAllTermekek() {
        return termekService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Termek> getTermekById(@PathVariable Integer id) {
        return termekService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Termek createTermek(@RequestBody Termek termek) {
        return termekService.save(termek);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Termek> updateTermek(@PathVariable Integer id, @RequestBody Termek termek) {
        try {
            Termek updatedTermek = termekService.update(id, termek);
            return ResponseEntity.ok(updatedTermek);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTermek(@PathVariable Integer id) {
        termekService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
