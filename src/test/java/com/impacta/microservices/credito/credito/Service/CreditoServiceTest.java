package com.impacta.microservices.credito.credito.Service;

import com.impacta.microservices.credito.credito.domain.TipoConta;
import com.impacta.microservices.credito.credito.exceptions.ContaIdNotFoundException;
import com.impacta.microservices.credito.credito.exceptions.TipoContaBadRequestException;
import com.impacta.microservices.credito.credito.exceptions.TipoContaNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.impacta.microservices.credito.credito.domain.Credito;
import com.impacta.microservices.credito.credito.repository.CreditoRepository;
import com.impacta.microservices.credito.credito.service.CreditoService;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
    public void criarCreditoTest(){
        final Integer idTransacao = 1;
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final Credito credito = new Credito(idTransacao, contaId, valorCredito, clienteId, tipoConta);

        final Credito result = creditoService.criarCredito(credito);

        assertEquals(contaId, result.getContaId());
        assertEquals(clienteId, result.getClienteId());
        assertEquals(tipoConta, result.getTipoConta());
    }

    @Test(expected = TipoContaBadRequestException.class)
    public void RetornarTipoContaBadRequestExceptionQuandoTentarCadastrarTipoContaIncorreta(){
        creditoService.criarCredito(new Credito(1, 1, 0.0, 1, "teste"));
    }

    @Test
    public void ListarContas(){
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final Credito credito1 = new Credito(1, contaId, valorCredito, clienteId, tipoConta);
        final Credito credito2 = new Credito(2, contaId, valorCredito, clienteId, tipoConta);
        creditoService.criarCredito(credito1);
        creditoService.criarCredito(credito2);

        final List<Credito> result = creditoService.listarContas();

        assertEquals(2, result.size());
    }

    @Test
    public void ConsultarTransacoesPorTipoConta(){
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final Credito credito1 = new Credito(1, contaId, valorCredito, clienteId, tipoConta);
        final Credito credito2 = new Credito(2, contaId, valorCredito, clienteId, tipoConta);
        creditoService.criarCredito(credito1);
        creditoService.criarCredito(credito2);

        final List<Credito> result = creditoService.consultaTransacoesTipoConta(tipoConta);

        assertEquals(2, result.size());
        assertEquals("contacorrente", result.get(0).getTipoConta());
    }

    @Test(expected = TipoContaNotFoundException.class)
    public void RetornarTipoContaNotFoundExceptionQuandoConsultarTipoContaInexistente(){
        creditoService.consultaTransacoesTipoConta("teste");
    }

    @Test
    public void consultaContaIdContaCorrenteTest(){
        final Integer idTransacao = 1;
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.contacorrente.toString();
        final Credito credito = new Credito(idTransacao, contaId, valorCredito, clienteId, tipoConta);
        creditoService.criarCredito(credito);

        final List<Credito> result = creditoService.consultaContaIdContaCorrente(contaId);

        assertEquals(contaId, result.get(0).getContaId());
        assertEquals(clienteId, result.get(0).getClienteId());
        assertEquals(tipoConta, result.get(0).getTipoConta());
    }

    @Test(expected = ContaIdNotFoundException.class)
    public void RetornarContaIdNotFoundExceptionQuandoConsultarContaIdContaCorrenteInexistente(){
        creditoService.consultaContaIdContaCorrente(999);
    }

    @Test
    public void consultaContaIdInvestimentoTest(){
        final Integer idTransacao = 1;
        final Integer contaId = 1;
        final Double valorCredito = 20.0;
        final Integer clienteId = 1;
        final String tipoConta = TipoConta.investimento.toString();
        final Credito credito = new Credito(idTransacao, contaId, valorCredito, clienteId, tipoConta);
        creditoService.criarCredito(credito);

        final List<Credito> result = creditoService.consultaContaIdInvestimento(contaId);

        assertEquals(contaId, result.get(0).getContaId());
        assertEquals(clienteId, result.get(0).getClienteId());
        assertEquals(tipoConta, result.get(0).getTipoConta());
    }

    @Test(expected = ContaIdNotFoundException.class)
    public void RetornarContaIdNotFoundExceptionQuandoConsultarContaIdInvestimentoInexistente(){
        creditoService.consultaContaIdInvestimento(888);
    }

    @Test
    public void consultaSaldoContaIdContaCorrenteTest(){
        final Integer contaId = 1;
        final Credito credito = new Credito(1, contaId, 200.0, 1, TipoConta.contacorrente.toString());
        creditoService.criarCredito(credito);

        final Credito credito2 = new Credito(2, contaId, 300.0, 1, TipoConta.contacorrente.toString());
        creditoService.criarCredito(credito2);

        Double result = creditoService.consultaSaldoContaIdContaCorrente(contaId);

        assertEquals(500.0, result, Double.POSITIVE_INFINITY);
    }

    @Test(expected = ContaIdNotFoundException.class)
    public void RetornarContaIdNotFoundExceptionQuandoConsultarSaldoContaIdContaCorrenteInexistente(){
        creditoService.consultaSaldoContaIdContaCorrente(999);
    }

    @Test
    public void consultaSaldoContaIdContaInvestimentoTest(){
        final Integer contaId = 1;
        final Credito credito = new Credito(1, contaId, 300.0, 1, TipoConta.investimento.toString());
        creditoService.criarCredito(credito);

        final Credito credito2 = new Credito(2, contaId, 300.0, 1, TipoConta.investimento.toString());
        creditoService.criarCredito(credito2);

        Double result = creditoService.consultaSaldoContaIdContaInvestimento(contaId);

        assertEquals(600.0, result, Double.POSITIVE_INFINITY);
    }

    @Test(expected = ContaIdNotFoundException.class)
    public void RetornarContaIdNotFoundExceptionQuandoConsultarSaldoContaIdInvestimentoInexistente(){
        creditoService.consultaSaldoContaIdContaInvestimento(888);
    }
}
