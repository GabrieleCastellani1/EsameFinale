package com.example.EsameFinale.service;

import com.example.EsameFinale.exception.IsFullException;
import com.example.EsameFinale.model.Film;
import com.example.EsameFinale.model.Sala;
import com.example.EsameFinale.model.response.GenericResponse;

import java.util.List;
import java.util.Optional;

public interface SalaService {
    Optional<Sala> getById(int id);

    List<Sala> getAll();

    Boolean deleteById(int id);

    Boolean deleteAll();

    Sala insert(Film film, int numMax);

    GenericResponse<Sala> update(int id, Film film, int numMax, int currentNum);

    void addSpettatore(int id) throws IsFullException;

    void addIncasso(int id, Double incasso);
}
