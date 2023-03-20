package com.example.EsameFinale.service;

import com.example.EsameFinale.model.Film;
import com.example.EsameFinale.model.response.GenericResponse;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    Optional<Film> getById(int id);

    List<Film> getAll();

    Boolean deleteById(int id);

    Boolean deleteAll();

    Film insert(String titolo, String autore, String produttore, String genere, int etaMinima, int durata);

    GenericResponse<Film> update(int id, String titolo, String autore, String produttore, String genere, int etaMinima, int durata);
}
