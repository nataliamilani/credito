package com.impacta.microservices.credito.credito.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditoTest {

    @Test
    public void CriarCredito() {
        final Integer idTransacao = 1;
        final Integer contaId = 1;
        final double valorCredito = 10.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();

        final Credito credito = new Credito(idTransacao, contaId, valorCredito, clienteId, tipoConta);

        assertEquals(idTransacao, credito.getIdTransacao());
        assertEquals(contaId, credito.getContaId());
        assertEquals(valorCredito, credito.getValorCredito());
        assertEquals(clienteId, credito.getClienteId());
        assertEquals(tipoConta, credito.getTipoConta());
    }
}