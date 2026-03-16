package com.vizsgaremek.raktar.controller;

import com.vizsgaremek.raktar.entity.enums.RendelesStatusz;
import com.vizsgaremek.raktar.service.RendelesService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rendelesek")
@RequiredArgsConstructor
public class RendelesController {
    private final RendelesService rendelesService;

    @PutMapping("/{id}/statusz")
    public void statuszModositas(@PathVariable Integer id, @RequestBody StatuszRequest request){
        rendelesService.statuszModositas(id, request.getUjStatusz(), request.getUserId());
    }

    @Data
    public static class StatuszRequest{
        private RendelesStatusz ujStatusz;
        private Integer userId;
    }
}
