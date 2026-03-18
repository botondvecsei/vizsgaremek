package com.vizsgaremek.raktar.controller;

import com.vizsgaremek.raktar.service.BeszerzesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/beszerzes")
@RequiredArgsConstructor
public class BeszerzesController {
    @Autowired
    private BeszerzesService beszerzesService;

    @PostMapping("/futtatas{rendszerUserId}")
    public void automatikusBeszerzesInditas(@PathVariable Integer rendszerUserId){
        beszerzesService.automatikusBeszerzesFuttatasa(rendszerUserId);
    }
}
