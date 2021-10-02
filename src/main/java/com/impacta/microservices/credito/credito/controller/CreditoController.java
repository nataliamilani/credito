package com.impacta.microservices.credito.credito.controller;


import java.util.List;
import com.impacta.microservices.credito.credito.controller.response.SaldoCreditoResponse;
import com.impacta.microservices.credito.credito.domain.Credito;
import com.impacta.microservices.credito.credito.domain.TipoConta;
import com.impacta.microservices.credito.credito.service.CreditoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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

    //GET PARA CONSULTAR TODAS AS TRANSACOES POR TIPO CONTA
    @GetMapping(path = "/contas")
    public List<Credito> consultaTransacoesPorTipo(@RequestParam(value = "tipoConta", required = true) String tipoConta) {
        return creditoService.consultaTransacoesTipoConta(tipoConta);
    }

    //GET PARA CONSULTAR TODAS AS TRANSACOES DAS CONTAS
    @GetMapping(path = "/listar/contas")
    public List<Credito> listarContas() {
        return creditoService.listarContas();
    }

    //GET PARA CONSULTAR TRANSACOES DE CREDITO POR CONTA CORRENTE
    @GetMapping(path = "/extrato/contacorrente/{contaId}")
	public List<Credito> consultaExtratoContaCorrente(@PathVariable("contaId") Integer contaId) {
		return  creditoService.consultaContaIdContaCorrente(contaId);
	}

    //GET PARA CONSULTAR TRANSACOES DE CREDITO POR CONTA INVESTIMENTO
    @GetMapping(path = "/extrato/investimento/{contaId}") 
    public List<Credito> consultaExtratoContaInvestimento(@PathVariable("contaId") Integer contaId) {
        return creditoService.consultaContaIdInvestimento(contaId);
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
