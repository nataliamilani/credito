package com.impacta.microservices.credito.credito.repository;

import com.impacta.microservices.credito.credito.domain.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CreditoRepository extends JpaRepository<Credito, Integer>{

    List<Credito> findByContaIdAndTipoConta(Integer contaId, String tipoConta);

    List<Credito> findByTipoConta(@Param("tipoConta") String tipoConta);

    @Query(value = "select * from credito", nativeQuery = true )
    List<Credito> listarContas();

    @Query(value = "select SUM(valor_credito) from credito where conta_id = ?1", nativeQuery = true)
    double findByContaIdSaldoCredito(Integer contaId);

    @Query(value = "select SUM(valor_credito) from credito where conta_id = ?1 and tipo_conta = ?2", nativeQuery = true)
    double findBySaldoCreditoPorTipoConta(Integer contaId, String tipoConta);
}
