package com.impacta.microservices.credito.credito.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "credito")
public class Credito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Integer idTransacao;
   
    @Column(name = "conta_id")
    private Integer contaId;

    @Column(name = "valor_credito")
    private Double valorCredito;

    @Column(name = "cliente_id")
    private Integer clienteId;

    @Column(name = "tipo_conta")
    private String tipoConta;

    
    public Credito() {
        super();
    }

    @JsonCreator
    public Credito( @JsonProperty("id_transacao") Integer idTransacao, 
                    @JsonProperty("conta_id") Integer contaId, 
                    @JsonProperty("valor_credito") Double valorCredito, 
                    @JsonProperty("cliente_id") Integer clienteId,
                    @JsonProperty("tipo_conta") String tipoConta)
    {
        this.idTransacao = idTransacao;
        this.contaId = contaId;
        this.valorCredito = valorCredito;
        this.clienteId = clienteId;
        this.tipoConta = tipoConta;
    }

    public Integer getIdTransacao() {
        return this.idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Integer getContaId() {
        return this.contaId;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }

    public Double getValorCredito() {
        return this.valorCredito;
    }

    public void setValorCredito(Double valorCredito) {
        this.valorCredito = valorCredito;
    }

    public Integer getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoConta() {
        return this.tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

}
