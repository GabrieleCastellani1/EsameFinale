package com.example.EsameFinale.service;

import com.example.EsameFinale.model.Spettatore;
import com.example.EsameFinale.repository.BigliettoRepository;
import com.example.EsameFinale.repository.SpettatoreRepository;
import com.example.EsameFinale.service.impl.BigliettoServiceImpl;
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
public class BigliettoServiceTest {
    private BigliettoService bigliettoService;

    @Mock
    private BigliettoRepository bigliettoRepository;

    @Mock
    private SalaService salaService;

    @BeforeEach
    public void init(){
        bigliettoService = new BigliettoServiceImpl(bigliettoRepository, salaService);
    }
}
