package com.impacta.microservices.credito.credito.Controller;

import com.impacta.microservices.credito.credito.controller.response.SaldoCreditoResponse;
import com.impacta.microservices.credito.credito.domain.Credito;
import com.impacta.microservices.credito.credito.domain.TipoConta;
import com.impacta.microservices.credito.credito.service.CreditoService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CreditoControllerTest {

    @Autowired
    private TestRestTemplate template;

    @MockBean
    private CreditoService creditoService;

    @Test
    public void criarCreditoTest(){
        final Integer idTransacao = 1;
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final Credito credito = new Credito(idTransacao, contaId, valorCredito, clienteId, tipoConta);

        when(creditoService.criarCredito(credito)).thenReturn(credito);

        final ResponseEntity<Credito> response = template
                .postForEntity("/credito", credito, Credito.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void consultarTransacoesPorTipoTest(){
        final Integer idTransacao = 1;
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final Credito credito = new Credito(idTransacao, contaId, valorCredito, clienteId, tipoConta);

        when(creditoService.consultaTransacoesTipoConta(tipoConta)).thenReturn(List.of(credito));

        final ResponseEntity<Credito[]> response = template
                .getForEntity("/credito/contas" + "?tipoConta=" + tipoConta, Credito[].class );
        final List<Credito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(1, result.size());
    }

    @Test
    public void listarTransacoesRealizadas(){
        final Credito credito1 = new Credito(1, 1, 20.0, 1, TipoConta.contacorrente.toString());
        final Credito credito2 = new Credito(2, 2, 80.0, 1, TipoConta.investimento.toString());

        when(creditoService.listarContas()).thenReturn(List.of(credito1, credito2));

        final ResponseEntity<Credito[]> response = template
                .getForEntity("/credito/listar/contas", Credito[].class );
        final List<Credito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, result.size());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoContaCorrenteRetornarExtratoDaConta(){
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final Credito credito1 = new Credito(1, contaId, valorCredito, clienteId, tipoConta);
        final Credito credito2 = new Credito(2, contaId, valorCredito, clienteId, tipoConta);

        when(creditoService.consultaContaIdContaCorrente(contaId)).thenReturn(List.of(credito1, credito2));

        final ResponseEntity<Credito[]> response = template
                .getForEntity("/credito/extrato/contacorrente/" + contaId, Credito[].class );
        final List<Credito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, result.size());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoContaInvestimentoExtratoDaConta(){
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.investimento.toString();
        final Credito credito1 = new Credito(1, contaId, valorCredito, clienteId, tipoConta);
        final Credito credito2 = new Credito(2, contaId, valorCredito, clienteId, tipoConta);

        when(creditoService.consultaContaIdInvestimento(contaId)).thenReturn(List.of(credito1, credito2));

        final ResponseEntity<Credito[]> response = template
                .getForEntity("/credito/extrato/investimento/" + contaId, Credito[].class );
        final List<Credito> result = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, result.size());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoInvestimentoRetornarSaldoTotalDaConta(){
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.investimento.toString();
        final double saldoConta = 40.0;
        final Credito credito1 = new Credito(1, contaId, valorCredito, clienteId, tipoConta);
        final Credito credito2 = new Credito(2, contaId, valorCredito, clienteId, tipoConta);
        creditoService.criarCredito(credito1);
        creditoService.criarCredito(credito2);

        when(creditoService.consultaSaldoContaIdContaInvestimento(contaId)).thenReturn(saldoConta);

        final ResponseEntity<SaldoCreditoResponse> response = template
                .getForEntity("/credito/saldo/investimento/" + contaId, SaldoCreditoResponse.class);
        final SaldoCreditoResponse result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(40.0, result.getSaldoCredito());
    }

    @Test
    public void aoBuscarPorContaIdDoTipoContaCorrenteRetornarSaldoTotalDaConta(){
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final double saldoConta = 40.0;
        final Credito credito1 = new Credito(1, contaId, valorCredito, clienteId, tipoConta);
        final Credito credito2 = new Credito(2, contaId, valorCredito, clienteId, tipoConta);
        creditoService.criarCredito(credito1);
        creditoService.criarCredito(credito2);

        when(creditoService.consultaSaldoContaIdContaCorrente(contaId)).thenReturn(saldoConta);

        final ResponseEntity<SaldoCreditoResponse> response = template
                .getForEntity("/credito/saldo/contacorrente/" + contaId, SaldoCreditoResponse.class);
        final SaldoCreditoResponse result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(40.0, result.getSaldoCredito());
    }
}
