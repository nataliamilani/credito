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
    private Integer id_transacao;
   
    private Integer contaId;

    private BigDecimal valor_credito;

    public Credito() {
        super();
    }

    public Credito(Integer contaId, BigDecimal valor_credito){
        this.contaId = contaId;
        this.valor_credito = valor_credito;
    }

    public Integer getId_transacao() {
        return this.id_transacao;
    }

    public void setId_transacao(Integer id_transacao) {
        this.id_transacao = id_transacao;
    }

    public Integer getContaId() {
        return this.contaId;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }

    public BigDecimal getValor_credito() {
        return this.valor_credito;
    }

    public void setValor_credito(BigDecimal valor_credito) {
        this.valor_credito = valor_credito;
    }

}
