package br.com.db.model.transacao;

import br.com.db.model.usuario.Conta;

import java.util.Date;

public class Transacao {
    private Conta conta;
    private String tipo;
    private double montante;
    private Date data;
    private Ativo ativo;

    // Construtor
    public Transacao(Conta conta,String tipo, double montante, Date data, Ativo ativo) {
        this.conta = conta;
        this.tipo = tipo;
        this.montante = montante;
        this.data = data;
        this.ativo = ativo;
    }

    // Getters e Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMontante() {
        return montante;
    }

    public void setMontante(double montante) {
        this.montante = montante;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public Conta getConta() {return conta;}

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }
    public void setConta(Conta conta) {this.conta = conta;}

}

