package br.com.db.model.usuario;

import java.util.Date;

public class ContaInvestimento extends Conta {
    private double rendimentoEsperado;

    public ContaInvestimento(long idConta, String numeroConta, double saldo, String tipoMoeda, Date dataCriacao, String tipoConta, double rendimentoEsperado) {
        super(idConta, numeroConta, saldo, tipoMoeda, dataCriacao, tipoConta);
        this.rendimentoEsperado = rendimentoEsperado;
    }

    public double getRendimentoEsperado() {
        return rendimentoEsperado;
    }

    public void setRendimentoEsperado(double rendimentoEsperado) {
        this.rendimentoEsperado = rendimentoEsperado;
    }

    @Override
    public void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }
}
