package com.vizsgaremek.raktar.repository;

import com.vizsgaremek.raktar.entity.RendelesTetel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RendelesTetelRepository extends JpaRepository<RendelesTetel, Integer> {
    List<RendelesTetel> findByRendelesId(Integer rendelesId);
}