package com.vizsgaremek.raktar.repository;

import com.vizsgaremek.raktar.entity.Termek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TermekRepository extends JpaRepository<Termek, Integer> {
    @Query("SELECT t FROM Termek t WHERE t.jelenlegiSzint < t.minSzint")
    List<Termek> findKritikusKeszlet();
}