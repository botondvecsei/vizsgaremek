package com.vizsgaremek.raktar.controller;

import com.vizsgaremek.raktar.entity.enums.RendelesStatusz;
import com.vizsgaremek.raktar.service.RendelesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rendelesek")
@RequiredArgsConstructor
public class RendelesController {
    @Autowired
    private  RendelesService rendelesService;

    @PutMapping("/{id}/statusz")
    public void statuszModositas(@PathVariable Integer id, @RequestBody StatuszRequest request){
        rendelesService.statuszModositas(id, request.getUjStatusz(), request.getUserId());

    }

    public static class StatuszRequest{
        private RendelesStatusz ujStatusz;
        private Integer userId;

        public RendelesStatusz getUjStatusz() {
            return ujStatusz;
        }

        public void setUjStatusz(RendelesStatusz ujStatusz) {
            this.ujStatusz = ujStatusz;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }
    }


}
