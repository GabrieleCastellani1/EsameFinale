package com.example.EsameFinale.repository;

import com.example.EsameFinale.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {
}
