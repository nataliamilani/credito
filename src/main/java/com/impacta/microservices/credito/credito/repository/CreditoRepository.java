package com.impacta.microservices.credito.credito.repository;

import com.impacta.microservices.credito.credito.domain.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

//import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CreditoRepository extends JpaRepository<Credito, Integer>{

    List<Credito> findByContaIdAndTipoConta(Integer contaId, String tipoConta);

    @Query(value = "select SUM(valor_credito) from credito where conta_id = ?1", nativeQuery = true)
    Double findByContaIdSaldoCredito(Integer contaId);

    @Query(value = "select SUM(valor_credito) from credito where conta_id = ?1 and tipo_conta = ?2", nativeQuery = true)
    Double findBySaldoCreditoPorTipoConta(Integer contaId, String tipoConta);
    
}
