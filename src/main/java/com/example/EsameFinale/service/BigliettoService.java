package com.example.EsameFinale.service;

import com.example.EsameFinale.model.Biglietto;
import com.example.EsameFinale.model.response.GenericResponse;

import java.util.List;
import java.util.Optional;

public interface BigliettoService {
    GenericResponse<Biglietto> insert(int numPosto, int idSala, int eta);

    Optional<Biglietto> getById(int id);

    List<Biglietto> getAll();

    Boolean deleteById(int id);

    Boolean deleteAll();
}
