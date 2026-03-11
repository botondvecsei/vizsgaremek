package com.vizsgaremek.raktar.controller;

import com.vizsgaremek.raktar.entity.enums.MozgasTipus;
import com.vizsgaremek.raktar.service.KeszletService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/keszlet")
@RequiredArgsConstructor
public class KeszletController {
    private final KeszletService keszletService;

    @PostMapping("/mozgas")
    public void keszletMozgasRogzites(@RequestBody MozgasRequest request){
        keszletService.keszletModositas(
                request.getTermekId(),
                request.getMennyiseg(),
                request.getTipus(),
                request.getMegjegyzes()
        );
    }
    @Data
    public static class MozgasRequest{
        private Integer termekId;
        private Integer mennyiseg;
        private MozgasTipus tipus;
        private String megjegyzes;
    }
}
