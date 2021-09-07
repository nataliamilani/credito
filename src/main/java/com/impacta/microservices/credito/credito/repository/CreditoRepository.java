package com.impacta.microservices.credito.credito.repository;

import com.impacta.microservices.credito.credito.domain.Credito;
//import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;

public interface CreditoRepository extends CrudRepository<Credito, Integer>{

}
