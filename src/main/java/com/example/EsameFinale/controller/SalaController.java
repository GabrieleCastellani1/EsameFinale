package com.example.EsameFinale.controller;

import com.example.EsameFinale.model.Sala;
import com.example.EsameFinale.model.response.GenericResponse;
import com.example.EsameFinale.service.SalaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sala")
public class SalaController {
    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping("/{id}")
    public Sala getById(@PathVariable int id){
        Optional<Sala> sala = salaService.getById(id);
        if(sala.isPresent()){
            return sala.get();
        }else{
            return null;
        }
    }

    @PostMapping()
    public Sala insert(@RequestBody Sala sala){
        return salaService.insert(sala.getFilm(), sala.getNumMax());
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable int id){
        return salaService.deleteById(id);
    }

    @DeleteMapping()
    public Boolean deleteAll(){
        return salaService.deleteAll();
    }

    @GetMapping()
    public List<Sala> getAll(){
        return salaService.getAll();
    }

    @PutMapping()
    public GenericResponse<Sala> update(@RequestBody Sala sala){
        return salaService.update(sala.getId(), sala.getFilm(), sala.getNumMax(), sala.getCurrentNum());
    }

    @GetMapping("/incasso")
    public Double getIncassoTotale(){
        List<Sala> sale = getAll();
        double total = 0;
        for (Sala sala : sale) {
            total += sala.getIncasso();
        }
        return total;
    }

}
