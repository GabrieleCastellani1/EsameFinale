package com.example.EsameFinale.service;

import com.example.EsameFinale.model.Spettatore;
import com.example.EsameFinale.repository.SpettatoreRepository;
import com.example.EsameFinale.service.impl.SpettatoreServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.sql.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application_test.properties")
public class SpettatoreServiceTest {

    private SpettatoreService spettatoriService;

    @Mock
    private SpettatoreRepository spettatoriRepository;

    @Mock
    private BigliettoService bigliettoService;

    @BeforeEach
    public void init(){
        spettatoriService = new SpettatoreServiceImpl(spettatoriRepository, bigliettoService);
    }

    @Test
    public void test_getById(){
        Spettatore spettatore = Spettatore
                .builder()
                .id(1)
                .nome("nome")
                .cognome("cognome")
                .dataDiNascita(new Date(1980, 4, 15))
                .build();
        Mockito.when(spettatoriRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(spettatore));

        Optional<Spettatore> response = spettatoriService.getById(spettatore.getId());
        Assertions.assertNotNull(response.get());
        Assertions.assertEquals(spettatore.getId(), response.get().getId());
        Assertions.assertEquals(spettatore.getNome(), response.get().getNome());
        Assertions.assertEquals(spettatore.getCognome(), response.get().getCognome());
        Assertions.assertEquals(spettatore.getDataDiNascita(), response.get().getDataDiNascita());
    }
}

