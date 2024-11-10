package br.com.db.model.transacao;

import br.com.db.model.usuario.Conta;

import java.util.Date;

public class Ativo {

    private String nome;
    private String codigo;
    private double precoAtual;
    private Date dataAtualizacao;

    // Construtor
    public Ativo(String nome, String codigo, double precoAtual, Date dataAtualizacao) {
        this.nome = nome;
        this.codigo = codigo;
        this.precoAtual = precoAtual;
        this.dataAtualizacao = dataAtualizacao;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(double precoAtual) {
        this.precoAtual = precoAtual;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
