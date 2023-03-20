package com.example.EsameFinale.repository;

import com.example.EsameFinale.model.Spettatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpettatoreRepository extends JpaRepository<Spettatore, Integer> {
}
