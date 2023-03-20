package com.example.EsameFinale.controller;

import com.example.EsameFinale.model.Biglietto;
import com.example.EsameFinale.model.Sala;
import com.example.EsameFinale.model.Spettatore;
import com.example.EsameFinale.model.request.BigliettoRequest;
import com.example.EsameFinale.model.response.GenericResponse;
import com.example.EsameFinale.service.SalaService;
import com.example.EsameFinale.service.SpettatoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spettatore")
public class SpettatoreController {
    private final SpettatoreService spettatoreService;

    public SpettatoreController(SpettatoreService spettatoreService) {
        this.spettatoreService = spettatoreService;
    }

    @GetMapping("/{id}")
    public Spettatore getById(@PathVariable int id){
        Optional<Spettatore> spettatore = spettatoreService.getById(id);
        if(spettatore.isPresent()){
            return spettatore.get();
        }else{
            return null;
        }
    }

    @PostMapping()
    public Spettatore insert(@RequestBody Spettatore spettatore){
        return spettatoreService.insert(spettatore.getNome(), spettatore.getCognome(), spettatore.getDataDiNascita());
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable int id){
        return spettatoreService.deleteById(id);
    }

    @DeleteMapping()
    public Boolean deleteAll(){
        return spettatoreService.deleteAll();
    }

    @GetMapping()
    public List<Spettatore> getAll(){
        return spettatoreService.getAll();
    }

    @PutMapping()
    public GenericResponse<Biglietto> compraBiglietto(@RequestBody BigliettoRequest request){
        return spettatoreService.compraBiglietto(request.getIdSala(), request.getNumPosto(), request.getEta());
    }
}
