package com.impacta.microservices.credito.credito.Service;
/*
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//import java.util.List;

import com.impacta.microservices.credito.credito.domain.Credito;
import com.impacta.microservices.credito.credito.repository.CreditoRepository;
import com.impacta.microservices.credito.credito.service.CreditoService;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class CreditoServiceTest {

    @Autowired
    private CreditoService creditoService;

    @Autowired
    private CreditoRepository creditoRepository;

    @Before
    public void setUp() {
        creditoRepository.deleteAll();
    }

    @Test
    public void salvarCreditoCriado(){
        final Integer id_transacao = 1;
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = "contacorrente";
        final Credito credito = new Credito(id_transacao, contaId, valorCredito, clienteId, tipoConta);

        final Credito result = creditoService.criarCredito(credito);

        assertEquals(contaId, result.getContaId());
        assertEquals(clienteId, result.getClienteId());
        assertEquals(tipoConta, result.getTipoConta());

    }


    
}
*/