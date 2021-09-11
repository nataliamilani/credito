package com.impacta.microservices.credito.credito.controller;

import java.net.UnknownHostException;
import java.util.List;

import com.impacta.microservices.credito.credito.controller.response.SaldoCreditoResponse;
import com.impacta.microservices.credito.credito.domain.Credito;
import com.impacta.microservices.credito.credito.service.CreditoService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Credito endpoint")
@RestController
@RequestMapping("/credito")
public class CreditoController {

    private final CreditoService creditoService;

    public CreditoController(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    @GetMapping(path = "/status_aplicacao")
    public String checarStatusAplicacao(){
        return "Aplicação online";
    }

    @PostMapping
    public Credito criarCredito(@RequestBody Credito credito){
        return creditoService.criarCredito(credito);
    }

    //GET PARA CONSULTAR TRANSACOES DE CREDITO POR CONTA CORRENTE
    @GetMapping(path = "/extrato/contacorrente/{contaId}")
	public List<Credito> consultaExtratoContaCorrente(@PathVariable("contaId") Integer contaId) throws UnknownHostException {
			
		return  creditoService.consultaContaIdContaCorrente(contaId);
		
	}

    //GET PARA CONSULTAR TRANSACOES DE CREDITO POR CONTA INVESTIMENTO
    @GetMapping(path = "/extrato/investimento/{contaId}") 
    public List<Credito> consultaExtratoContaInvestimento(@PathVariable("contaId") Integer contaId) throws UnknownHostException {
            
        List<Credito> extratoInvestimento = creditoService.consultaContaIdInvestimento(contaId);
        
        return extratoInvestimento;  
        
    }

    //GET PARA CONSOLIDAR OS VALORES DE CREDITO EM UMA CONTA CORRENTE
    @GetMapping(path = "/saldo/contacorrente/{contaId}")
	public SaldoCreditoResponse consultaSaldoConsolidadoContaCorrente(@PathVariable("contaId") Integer contaId) {

        SaldoCreditoResponse saldoCreditoResponse =
                new SaldoCreditoResponse(creditoService.consultaSaldoContaIdContaCorrente(contaId));

        return  saldoCreditoResponse;
		
	}

    //GET PARA CONSOLIDAR OS VALORES DE CREDITO EM UMA CONTA INVESTIMENTO
    @GetMapping(path = "/saldo/investimento/{contaId}")
    public SaldoCreditoResponse consultaSaldoConsolidadoContaInvestimento(@PathVariable("contaId") Integer contaId) {

        SaldoCreditoResponse saldoCreditoResponse =
                new SaldoCreditoResponse(creditoService.consultaSaldoContaIdContaInvestimento(contaId));

        return saldoCreditoResponse;
    }

}
