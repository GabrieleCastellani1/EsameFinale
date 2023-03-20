package com.example.EsameFinale.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "idfilm")
    private Film film;
    private Double incasso;
    @Column(name = "nummax")
    private int numMax;
    @Column(name = "currentnum")
    private int currentNum;
}
