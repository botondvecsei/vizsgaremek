package com.vizsgaremek.raktar.controller;

import com.vizsgaremek.raktar.entity.enums.MozgasTipus;
import com.vizsgaremek.raktar.service.KeszletService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/keszlet")
@RequiredArgsConstructor
public class KeszletController {
    @Autowired
    private KeszletService keszletService;

    @PostMapping("/mozgas")
    public void keszletMozgasRogzites(@RequestBody MozgasRequest request){
        keszletService.keszletModositas(
                request.getTermekId(),
                request.getMennyiseg(),
                request.getTipus(),
                request.getMegjegyzes()
        );

    }
    public static class MozgasRequest{
        private Integer termekId;
        private Integer mennyiseg;
        private MozgasTipus tipus;
        private String megjegyzes;

        public Integer getTermekId() {
            return termekId;
        }

        public Integer getMennyiseg() {
            return mennyiseg;
        }

        public MozgasTipus getTipus() {
            return tipus;
        }

        public String getMegjegyzes() {
            return megjegyzes;
        }
    }


}
