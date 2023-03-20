package com.example.EsameFinale.controller;

import com.example.EsameFinale.model.Film;
import com.example.EsameFinale.model.response.GenericResponse;
import com.example.EsameFinale.service.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/film")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{id}")
    public Film getById(@PathVariable int id){
        Optional<Film> film = filmService.getById(id);
        if(film.isPresent()){
            return film.get();
        }else{
            return null;
        }
    }

    @PostMapping()
    public Film insert(@RequestBody Film film){
        return filmService.insert(film.getTitolo(),
                film.getAutore(),
                film.getProduttore(),
                film.getGenere(),
                film.getEtaMinima(),
                film.getDurata());
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable int id){
        return filmService.deleteById(id);
    }

    @DeleteMapping()
    public Boolean deleteAll(){
        return filmService.deleteAll();
    }

    @GetMapping()
    public List<Film> getAll(){
        return filmService.getAll();
    }

    @PutMapping
    public GenericResponse<Film> update(@RequestBody Film film){
        return filmService.update(film.getId(),
                film.getTitolo(),
                film.getAutore(),
                film.getProduttore(),
                film.getGenere(),
                film.getEtaMinima(),
                film.getDurata());
    }
}
