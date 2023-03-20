package com.example.EsameFinale.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BigliettoRequest {
    private int idSala;
    private int numPosto;
    private int eta;
}
