package br.com.db.model.usuario;

public class ContaPoupanca extends Conta {
    private double taxaJuros;

    public ContaPoupanca(long idConta, String numeroConta, double saldo, String tipoMoeda, java.sql.Date dataCriacao, String tipoConta, double taxaJuros) {
        super(idConta, numeroConta, saldo, tipoMoeda, dataCriacao, tipoConta);
        this.taxaJuros = taxaJuros;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
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


