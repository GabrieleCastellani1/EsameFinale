package com.example.EsameFinale.service.impl;

import com.example.EsameFinale.model.Biglietto;
import com.example.EsameFinale.model.Film;
import com.example.EsameFinale.model.Spettatore;
import com.example.EsameFinale.model.response.GenericResponse;
import com.example.EsameFinale.repository.SpettatoreRepository;
import com.example.EsameFinale.service.BigliettoService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class SpettatoreServiceImpl {
    private final SpettatoreRepository spettatoreRepository;
    private final BigliettoService bigliettoService;

    public SpettatoreServiceImpl(SpettatoreRepository spettatoreRepository, BigliettoService bigliettoService) {
        this.spettatoreRepository = spettatoreRepository;
        this.bigliettoService = bigliettoService;
    }

    public Optional<Spettatore> getById(int id){
        return spettatoreRepository.findById(id);
    }

    public List<Spettatore> getAll(){
        return spettatoreRepository.findAll();
    }

    public Spettatore insert(String nome, String cognome, Date dataDiNascita){
        return spettatoreRepository.save(Spettatore.builder()
                .nome(nome)
                .cognome(cognome)
                .dataDiNascita(dataDiNascita)
                .build());
    }

    public GenericResponse<Biglietto> compraBiglietto(int id, int numPosto, int idSala){
        Optional<Spettatore> spettatore = getById(id);
        if(spettatore.isPresent()){
            GenericResponse<Biglietto> response =  bigliettoService.insert(numPosto, idSala, spettatore.get().getEta());
            spettatore.get().setBiglietto(response.getBody());
            return response;
        }else{
            return GenericResponse.<Biglietto>builder().errorMessage("spettatore non presente").build();
        }
    }

    public Boolean deleteById(int id){
        if(getById(id).isPresent()){
            spettatoreRepository.deleteById(id);
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    public Boolean deleteAll(){
        Boolean res = Boolean.TRUE;
        try {
            spettatoreRepository.deleteAll();
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }
}
