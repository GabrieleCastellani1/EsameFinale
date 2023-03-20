package com.example.EsameFinale.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Spettatore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    @OneToOne
    @JoinColumn(name = "idBiglietto")
    private Biglietto biglietto;

    public int getEta(){
        LocalDate date = dataDiNascita.toLocalDate();
        long years = ChronoUnit.YEARS.between(
                date,
                LocalDate.now( ZoneId.of( "Europe/Paris" ) )
        );
        return (int) years;
    }
}
