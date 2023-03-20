package com.example.EsameFinale.service.impl;

import com.example.EsameFinale.exception.IsFullException;
import com.example.EsameFinale.model.Biglietto;
import com.example.EsameFinale.model.Film;
import com.example.EsameFinale.model.Sala;
import com.example.EsameFinale.model.response.GenericResponse;
import com.example.EsameFinale.repository.BigliettoRepository;
import com.example.EsameFinale.service.BigliettoService;
import com.example.EsameFinale.service.SalaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BigliettoServiceImpl implements BigliettoService {
    private final BigliettoRepository bigliettoRepository;
    private final SalaService salaService;

    private final double prezzoBase = 8;

    public BigliettoServiceImpl(BigliettoRepository bigliettoRepository, SalaService salaService) {
        this.bigliettoRepository = bigliettoRepository;
        this.salaService = salaService;
    }

    @Override
    public GenericResponse<Biglietto> insert(int numPosto, int idSala, int eta){
        GenericResponse<Biglietto> response;
        Optional<Sala> sala = salaService.getById(idSala);
        if(sala.isPresent()){
            Film film = sala.get().getFilm();
            if(film.getEtaMinima() > eta){
                response = GenericResponse.<Biglietto>builder().errorMessage("film vietato ai minori").build();
            }else{
                try{
                    salaService.addSpettatore(idSala);
                    double prezzo = calcolaSconto(eta);
                    salaService.addIncasso(idSala, prezzo);
                    Biglietto biglietto = bigliettoRepository.save(Biglietto
                            .builder()
                            .idSala(idSala)
                            .numPosto(numPosto)
                            .prezzo(prezzo)
                            .build());
                    response = GenericResponse.<Biglietto>builder().body(biglietto).build();
                } catch (IsFullException e) {
                    response = GenericResponse.<Biglietto>builder().errorMessage("sala al completo").build();
                }
            }
        }else{
            response = GenericResponse.<Biglietto>builder().errorMessage("sala non trovata").build();
        }
        return response;
    }

    @Override
    public Optional<Biglietto> getById(int id){
        return bigliettoRepository.findById(id);
    }

    @Override
    public List<Biglietto> getAll(){
        return bigliettoRepository.findAll();
    }

    @Override
    public Boolean deleteById(int id){
        if(getById(id).isPresent()){
            bigliettoRepository.deleteById(id);
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean deleteAll(){
        Boolean res = Boolean.TRUE;
        try {
            bigliettoRepository.deleteAll();
        } catch (Exception e) {
            res = Boolean.FALSE;
        }
        return res;
    }

    private double calcolaSconto(int eta) {
        if(eta > 70){
            return prezzoBase*9/10;
        }else if (eta < 5){
            return prezzoBase*1/2;
        }else{
            return prezzoBase;
        }
    }
}
