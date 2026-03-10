package com.vizsgaremek.raktar.repository;

import com.vizsgaremek.raktar.entity.Keszletmozgas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KeszletmozgasRepository extends JpaRepository<Keszletmozgas, Integer> {
    List<Keszletmozgas> findByTermekIdOrderByDatumDesc(Integer termekId);
}