package com.example.EsameFinale.service.impl;

import com.example.EsameFinale.exception.IsFullException;
import com.example.EsameFinale.model.Film;
import com.example.EsameFinale.model.Sala;
import com.example.EsameFinale.model.response.GenericResponse;
import com.example.EsameFinale.repository.SalaRepository;
import com.example.EsameFinale.service.SalaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaServiceImpl implements SalaService {
    private final SalaRepository salaRepository;

    public SalaServiceImpl(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Override
    public Optional<Sala> getById(int id){
        return salaRepository.findById(id);
    }

    @Override
    public List<Sala> getAll(){
        return salaRepository.findAll();
    }

    @Override
    public Boolean deleteById(int id){
            if(getById(id).isPresent()){
                salaRepository.deleteById(id);
                return Boolean.TRUE;
            }else{
                return Boolean.FALSE;
            }
        }

    @Override
    public Boolean deleteAll(){
        Boolean res = Boolean.TRUE;
        try {
            salaRepository.deleteAll();
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }

    @Override
    public Sala insert(Film film, int numMax){
        return salaRepository.save(Sala.builder()
                .film(film)
                .incasso((double) 0)
                .numMax(numMax)
                .currentNum(0)
                .build());
    }

    @Override
    public GenericResponse<Sala> update(int id, Film film, int numMax, int currentNum){
        GenericResponse<Sala> response;
        Optional<Sala> sala = getById(id);
        if (sala.isPresent()) {
            sala.get().setFilm(film);
            sala.get().setNumMax(numMax);
            sala.get().setCurrentNum(currentNum);

            response = GenericResponse.<Sala>builder().body(salaRepository.save(sala.get())).build();
        }else {
            response = GenericResponse.<Sala>builder().errorMessage("sala non trovata").build();
        }
        return response;
    }

    @Override
    public void addSpettatore(int id) throws IsFullException {
        Optional<Sala> sala = getById(id);
        if (sala.isPresent()) {
            if(sala.get().getNumMax() < sala.get().getCurrentNum()){
                sala.get().setCurrentNum(sala.get().getCurrentNum()+1);
            }else{
                throw new IsFullException("Sala al completo");
            }
        }
    }

    @Override
    public void addIncasso(int id, Double incasso){
        Optional<Sala> sala = getById(id);
        if (sala.isPresent()) {
            sala.get().setIncasso(sala.get().getIncasso() + incasso);
        }
    }

}