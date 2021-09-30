package com.impacta.microservices.credito.credito.service;

import java.util.List;
import java.util.Objects;

import com.impacta.microservices.credito.credito.domain.Credito;
import com.impacta.microservices.credito.credito.exceptions.ContaIdNotFoundException;
import com.impacta.microservices.credito.credito.exceptions.TipoContaNotFoundException;
import com.impacta.microservices.credito.credito.repository.CreditoRepository;

import org.springframework.stereotype.Component;

@Component
public class CreditoService {

    private CreditoRepository repository;
    public CreditoService(CreditoRepository repository) {
        this.repository = repository;
    }

    public Credito criarCredito(Credito credito){
        return repository.save(credito);
    }

	public List<Credito> listarContas() {return repository.listarContas();}

	public List<Credito> consultaTransacoesTipoConta(String tipoConta) {

		if(!tipoConta.startsWith("contacorrente") && !tipoConta.startsWith("investimento")) {
			throw new TipoContaNotFoundException("Tipo de conta incorreta, por favor pesquisar por tipo contacorrente ou investimento");
		}

		var contas = repository.findByTipoConta(tipoConta);
		return contas;
	}

	public List<Credito> consultaContaIdContaCorrente(Integer contaId) {

		var listCredito = repository.findByContaIdAndTipoConta(contaId, "contacorrente");

		if (listCredito.isEmpty()) {
			throw new ContaIdNotFoundException("Não encontrada conta id: " + contaId);
		}
		return listCredito;
	}


	public List<Credito> consultaContaIdInvestimento(Integer contaId) {

		var listCredito = repository.findByContaIdAndTipoConta(contaId, "investimento");

		if(listCredito.isEmpty()) {
			throw new ContaIdNotFoundException("Não encontrada conta id: " + contaId);
		}
		return listCredito;
	}

	public double consultaSaldoContaIdContaCorrente(Integer contaId) {
		try{
			return  repository.findBySaldoCreditoPorTipoConta(contaId, "contacorrente");
		} catch (RuntimeException runtimeException) {
			throw new ContaIdNotFoundException("Não encontrada conta id: " + contaId);
		}
	}

    public double consultaSaldoContaIdContaInvestimento(Integer contaId) {
		try{
			return  repository.findBySaldoCreditoPorTipoConta(contaId, "investimento");
		}catch (RuntimeException runtimeException) {
			throw new ContaIdNotFoundException("Não encontrada conta id: " + contaId);
		}
	}
}
