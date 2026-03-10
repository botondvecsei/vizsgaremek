package com.vizsgaremek.raktar.repository;

import com.vizsgaremek.raktar.entity.Rendeles;
import com.vizsgaremek.raktar.entity.enums.RendelesStatusz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RendelesRepository extends JpaRepository<Rendeles, Integer> {
    List<Rendeles> findByStatusz(RendelesStatusz statusz);
}