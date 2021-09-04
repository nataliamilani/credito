package com.impacta.microservices.credito.credito.domain;

import org.springframework.data.annotation.Id;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Credito")
public class Credito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer contaId;
    private BigDecimal credito;

    public Credito() {
        super();
    }

    public Credito(BigDecimal credito, Integer contaId){
        this.credito = credito;
        this.contaId = contaId;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public Integer getContaId() {return contaId;}

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    public void setContaId(Integer contaId) {this.contaId = contaId;}
}
