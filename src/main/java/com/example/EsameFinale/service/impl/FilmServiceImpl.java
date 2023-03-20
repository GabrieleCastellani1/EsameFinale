package com.example.EsameFinale.service.impl;

import com.example.EsameFinale.model.Film;
import com.example.EsameFinale.model.Sala;
import com.example.EsameFinale.model.response.GenericResponse;
import com.example.EsameFinale.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl {
    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Optional<Film> getById(int id){
        return filmRepository.findById(id);
    }

    public List<Film> getAll(){
        return filmRepository.findAll();
    }

    public Boolean deleteById(int id){
        if(getById(id).isPresent()){
            filmRepository.deleteById(id);
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    public Boolean deleteAll() {
        Boolean res = Boolean.TRUE;
        try {
            filmRepository.deleteAll();
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }

    public Film insert(String titolo, String autore, String produttore, String genere, int etaMinima, int durata){
        return filmRepository.save(Film.builder()
                .titolo(titolo)
                .autore(autore)
                .produttore(produttore)
                .genere(genere)
                .etaMinima(etaMinima)
                .durata(durata)
                .build());
    }

    public GenericResponse<Film> update(int id, String titolo, String autore, String produttore, String genere, int etaMinima, int durata){
        GenericResponse<Film> response;
        Optional<Film> film = getById(id);
        if (film.isPresent()) {
            film.get().setTitolo(titolo);
            film.get().setAutore(autore);
            film.get().setProduttore(produttore);
            film.get().setGenere(genere);
            film.get().setEtaMinima(etaMinima);
            film.get().setDurata(durata);

            response = GenericResponse.<Film>builder().body(filmRepository.save(film.get())).build();
        }else {
            response = GenericResponse.<Film>builder().errorMessage("sala non trovata").build();
        }
        return response;
    }
}
