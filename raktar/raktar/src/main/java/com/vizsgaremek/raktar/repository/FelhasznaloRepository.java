package com.vizsgaremek.raktar.repository;

import com.vizsgaremek.raktar.entity.Felhasznalo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FelhasznaloRepository extends JpaRepository<Felhasznalo, Integer> {
    Optional<Felhasznalo> findByEmail(String email);
}