package com.vizsgaremek.raktar.controller;

import com.vizsgaremek.raktar.entity.Felhasznalo;
import com.vizsgaremek.raktar.service.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Felhasznalo login(@RequestBody LoginRequest request) {
        return authService.bejelentkezes(request.getEmail(), request.getJelszo());
    }

    @Data
    public static class LoginRequest {
        private String email;
        private String jelszo;
    }
}
