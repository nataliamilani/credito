package com.impacta.microservices.credito.credito.Controller;

import com.impacta.microservices.credito.credito.controller.response.SaldoCreditoResponse;
import com.impacta.microservices.credito.credito.domain.Credito;
import com.impacta.microservices.credito.credito.service.CreditoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
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
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
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

    @Test
    public void aoBuscarPorContaIdDoTipoContaCorrenteRetornarExtratoDaConta(){
        final Integer id_transacao = 1;
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Credito credito1 = new Credito(id_transacao, contaId, valorCredito, clienteId, tipoConta);
        final Credito credito2 = new Credito(id_transacao, contaId, valorCredito, clienteId, tipoConta);

        when(creditoService.consultaContaIdContaCorrente(contaId)).thenReturn(List.of(credito1, credito2));

        final ResponseEntity<Credito[]> response = template
                .getForEntity("/credito/extrato/contacorrente/" + contaId, Credito[].class );
        final List<Credito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, result.size());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoContaInvestimentoExtratoDaConta(){
        final Integer id_transacao = 1;
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "investimento";
        final Credito credito1 = new Credito(id_transacao, contaId, valorCredito, clienteId, tipoConta);
        final Credito credito2 = new Credito(id_transacao, contaId, valorCredito, clienteId, tipoConta);

        when(creditoService.consultaContaIdInvestimento(contaId)).thenReturn(List.of(credito1, credito2));

        final ResponseEntity<Credito[]> response = template
                .getForEntity("/credito/extrato/investimento/" + contaId, Credito[].class );
        final List<Credito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, result.size());
    }

    @Test
    public void aoBuscarPorUmaContaIdInexistenteRetornarExtratoVazio(){
        final Integer contaId = 2;

        when(creditoService.consultaContaIdInvestimento(contaId)).thenReturn(List.of());

        final ResponseEntity<Credito[]> response = template
                .getForEntity("/credito/extrato/investimento/" + contaId, Credito[].class );
        final List<Credito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(result.isEmpty());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoInvestimentoRetornarSaldoTotalDaConta(){
        final Integer id_transacao = 1;
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "investimento";
        final SaldoCreditoResponse saldoConta = new SaldoCreditoResponse(40.0);
        final Credito credito1 = new Credito(id_transacao, contaId, valorCredito, clienteId, tipoConta);
        final Credito credito2 = new Credito(id_transacao, contaId, valorCredito, clienteId, tipoConta);
        creditoService.criarCredito(credito1);
        creditoService.criarCredito(credito2);

        when(creditoService.consultaSaldoContaIdContaInvestimento(contaId)).thenReturn(saldoConta.getSaldoCredito());

        final var response = template
                .exchange("/credito/saldo/investimento/" + contaId, HttpMethod.GET, null, Object.class );

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void aoBuscarPorContaIdDoTipoContaCorrenteRetornarSaldoTotalDaConta(){
        final Integer id_transacao = 1;
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final SaldoCreditoResponse saldoConta = new SaldoCreditoResponse(40.0);
        final Credito credito1 = new Credito(id_transacao, contaId, valorCredito, clienteId, tipoConta);
        final Credito credito2 = new Credito(id_transacao, contaId, valorCredito, clienteId, tipoConta);
        creditoService.criarCredito(credito1);
        creditoService.criarCredito(credito2);

        when(creditoService.consultaSaldoContaIdContaInvestimento(contaId)).thenReturn(saldoConta.getSaldoCredito());

        final var response = template
                .exchange("/credito/saldo/contacorrente/" + contaId, HttpMethod.GET, null, Object.class);
                //.exchange("/credito/saldo/contacorrente/" + contaId, HttpMethod.GET, null, Double.TYPE );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }



}
