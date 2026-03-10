package com.vizsgaremek.raktar.repository;

import com.vizsgaremek.raktar.entity.Beszallito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeszallitoRepository extends JpaRepository<Beszallito, Integer> {
}