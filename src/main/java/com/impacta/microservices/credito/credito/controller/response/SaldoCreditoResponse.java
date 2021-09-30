package com.impacta.microservices.credito.credito.controller.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaldoCreditoResponse {

    private double saldoCredito;

    @JsonCreator
    public SaldoCreditoResponse(@JsonProperty("saldo_credito") double saldoCredito){
        this.saldoCredito = saldoCredito;
    }

    public double getSaldoCredito() {
        return saldoCredito;
    }

    public void setSaldoCredito(double saldoCredito) {
        this.saldoCredito = saldoCredito;
    }
}
