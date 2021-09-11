package com.impacta.microservices.credito.credito.controller.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaldoCreditoResponse {

    private Double saldoCredito;

    @JsonCreator
    public SaldoCreditoResponse(@JsonProperty("saldo_credito") Double saldoCredito){
        this.saldoCredito = saldoCredito;
    }

    public Double getSaldoCredito() {
        return saldoCredito;
    }

    public void setSaldoCredito(Double saldoCredito) {
        this.saldoCredito = saldoCredito;
    }

}