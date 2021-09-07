package com.impacta.microservices.credito.credito.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import com.impacta.microservices.credito.credito.repository.CreditoRepository;


@WebMvcTest
public class CreditoControllerTest {
    
    @Autowired
    private CreditoController creditoController;

    //mockando dependencia para não usar a implementacao real
    @MockBean
    private CreditoRepository repository;

    @BeforeEach
    public void setup(){
        standaloneSetup(this.creditoController);
    }
  

    @Test
    public void testSalvarComSucessoCreditarValor(){

    }
    



}
