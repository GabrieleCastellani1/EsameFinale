package com.example.EsameFinale.repository;

import com.example.EsameFinale.model.Biglietto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BigliettoRepository extends JpaRepository<Biglietto, Integer> {
}
