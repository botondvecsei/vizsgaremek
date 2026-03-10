package com.vizsgaremek.raktar.repository;

import com.vizsgaremek.raktar.entity.BeszallitoAr;
import com.vizsgaremek.raktar.entity.BeszallitoArId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BeszallitoArRepository extends JpaRepository<BeszallitoAr, BeszallitoArId> {
    Optional<BeszallitoAr> findFirstByTermekIdOrderByArAsc(Integer termekId);
}