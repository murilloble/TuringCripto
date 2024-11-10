package br.com.db.view;

import br.com.db.dao.ContaDao;
import br.com.db.exception.EntidadeNaoEncontradaException;
import br.com.db.model.usuario.Conta;
import br.com.db.model.usuario.ContaPoupanca;

import java.sql.SQLException;

public class AtualizacaoContaPoupancaView {
    public static void main(String[] args) {
        try {
            ContaDao dao = new ContaDao();
            Conta conta = dao.pesquisar(8);

            conta.setTipoConta("Poupanca");
            conta.setTipoMoeda("URL");
            conta.setSaldo(10);

            ContaPoupanca contaPoupanca = new ContaPoupanca(conta.idConta, conta.getNumeroConta(), conta.getSaldo(), conta.getTipoMoeda(), new java.sql.Date(new java.util.Date().getTime()), conta.getTipoConta(), 10 );

            dao.atualizar(contaPoupanca);
            dao.fecharConexao();
            System.out.println("Conta atualizada!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Conta não encontrada");
        }
    }
}