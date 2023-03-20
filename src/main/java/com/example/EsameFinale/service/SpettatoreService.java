package com.example.EsameFinale.service;

import com.example.EsameFinale.model.Biglietto;
import com.example.EsameFinale.model.Spettatore;
import com.example.EsameFinale.model.response.GenericResponse;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface SpettatoreService {
    Optional<Spettatore> getById(int id);

    List<Spettatore> getAll();

    Spettatore insert(String nome, String cognome, Date dataDiNascita);

    GenericResponse<Biglietto> compraBiglietto(int id, int numPosto, int idSala);

    Boolean deleteById(int id);

    Boolean deleteAll();
}
