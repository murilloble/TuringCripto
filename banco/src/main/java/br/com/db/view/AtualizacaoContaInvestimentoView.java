package br.com.db.view;

import br.com.db.dao.ContaDao;
import br.com.db.exception.EntidadeNaoEncontradaException;
import br.com.db.model.usuario.Conta;
import br.com.db.model.usuario.ContaInvestimento;

import java.sql.SQLException;

public class AtualizacaoContaInvestimentoView {
    public static void main(String[] args) {
        try {
            ContaDao dao = new ContaDao();
            Conta conta = dao.pesquisar(14);

            conta.setTipoConta("Investimento");
            conta.setTipoMoeda("URL");
            conta.setSaldo(10);

            ContaInvestimento contaInvestimento = new ContaInvestimento(conta.idConta, conta.getNumeroConta(), conta.getSaldo(), conta.getTipoMoeda(), new java.sql.Date(new java.util.Date().getTime()), conta.getTipoConta(), 10 );

            dao.atualizar(contaInvestimento);
            dao.fecharConexao();
            System.out.println("Conta atualizada!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Conta não encontrada");
        }
    }
}