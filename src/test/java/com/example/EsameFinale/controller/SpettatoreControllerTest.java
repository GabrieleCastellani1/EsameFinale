package com.example.EsameFinale.controller;

import com.example.EsameFinale.model.Spettatore;
import com.example.EsameFinale.service.SpettatoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SpettatoreController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class SpettatoreControllerTest {
    @MockBean
    private SpettatoreService spettatoreService;

    @Autowired
    private MockMvc client;

    private ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void insert_test() throws Exception {
        int id = 1;
        String nome = "nome";
        String cognome = "cognome";

        Spettatore spettatore = Spettatore.builder().id(id).nome(nome).cognome(cognome).build();

        Mockito.when(spettatoreService.insert(Mockito.anyString(), Mockito.anyString(), Mockito.any()))
                .thenReturn(spettatore);

        client.perform(MockMvcRequestBuilders.post("/spettatore")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(spettatore))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(nome))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cognome").value(cognome));
    }
}
