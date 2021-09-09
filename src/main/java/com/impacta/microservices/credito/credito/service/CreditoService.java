package com.impacta.microservices.credito.credito.service;

import java.util.List;

import com.impacta.microservices.credito.credito.domain.Credito;
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


	public List<Credito> consultaContaIdContaCorrente(Integer contaId) {
			
		return  repository.findByContaIdAndTipoConta(contaId, "contacorrente");
		
	}
	public List<Credito> consultaContaIdInvestimento(Integer contaId) {
			
		return  repository.findByContaIdAndTipoConta(contaId, "investimento");
		
	}

	public Double consultaSaldoContaId(Integer contaId) {
		
		return  repository.findByContaIdSaldoCredito(contaId);
		
	}

	public Double consultaSaldoContaIdContaCorrente(Integer contaId) {
		
		return  repository.findBySaldoCreditoPorTipoConta(contaId, "contacorrente");
		
	}

    public Double consultaSaldoContaIdContaInvestimento(Integer contaId) {
		
		return  repository.findBySaldoCreditoPorTipoConta(contaId, "investimento");
		
	}

}

