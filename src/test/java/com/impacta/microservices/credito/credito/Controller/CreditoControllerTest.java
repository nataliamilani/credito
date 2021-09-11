package com.impacta.microservices.credito.credito.Controller;
/*
import com.impacta.microservices.credito.credito.domain.Credito;
import com.impacta.microservices.credito.credito.service.CreditoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase
public class CreditoControllerTest {

    @Autowired
    private TestRestTemplate template;

    @MockBean
    private CreditoService creditoService;

    @Test
    public void criarCreditoTest(){
        final Integer id_transacao = 1;
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Credito credito = new Credito(id_transacao, contaId, valorCredito, clienteId, tipoConta);

        when(creditoService.criarCredito(credito)).thenReturn(credito);

        final ResponseEntity<Credito> response = template
                .postForEntity("/credito", credito, Credito.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
*/