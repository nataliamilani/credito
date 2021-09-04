package com.impacta.microservices.credito.credito.repository;

import com.impacta.microservices.credito.credito.domain.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditoRepository extends JpaRepository<Credito, Long>{

}
