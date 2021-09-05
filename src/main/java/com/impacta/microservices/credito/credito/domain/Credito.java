package com.impacta.microservices.credito.credito.domain;

import java.io.Serializable;
import java.math.BigDecimal;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Credito")
public class Credito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
   
    private Integer contaId;

    private BigDecimal credito;

    public Credito() {
        super();
    }

    public Credito(Integer contaId, BigDecimal credito){
        this.contaId = contaId;
        this.credito = credito;
        
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }
    public Integer getContaId() {return contaId;}

    public void setContaId(Integer contaId) {
        this.contaId = contaId;}
}
