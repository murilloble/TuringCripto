package br.com.db.model.usuario;

import java.util.Date;

public class Conta {
    public long idConta;
    protected String numeroConta;
    protected double saldo;
    protected String tipoMoeda;
    protected Date dataCriacao;

    protected String tipoConta;

    // Construtor
    public Conta(long idConta, String numeroConta, double saldo, String tipoMoeda, Date dataCriacao, String tipoConta) {
        this.idConta = idConta;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.tipoMoeda = tipoMoeda;
        this.dataCriacao = dataCriacao;
        this.tipoConta = tipoConta;
    }

    // Métodos comuns
    public long getIdConta() {
        return idConta;
    }
    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTipoMoeda() {
        return tipoMoeda;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }
    public String getTipoConta(){ return tipoConta; }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public void setIdConta (long idConta) {this.idConta = idConta;}

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTipoMoeda(String tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setTipoConta(String tipoConta) {this.tipoConta = tipoConta; }

    // Métodos abstratos
    public void sacar(double valor){};
    public void depositar(double valor){};
}

