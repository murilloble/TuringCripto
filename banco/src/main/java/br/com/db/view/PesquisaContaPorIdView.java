package br.com.db.view;

import br.com.db.dao.ContaDao;
import br.com.db.exception.EntidadeNaoEncontradaException;
import br.com.db.model.usuario.Conta;

import java.sql.SQLException;

public class PesquisaContaPorIdView {
    public static void main(String[] args) {
        try {
            ContaDao dao = new ContaDao();
            Conta conta = dao.pesquisar(14);
            System.out.println("Numero da Conta: " + conta.getNumeroConta()+ ", Moeda: " + conta.getTipoMoeda() + ", R$ " + conta.getSaldo());
            System.out.println("Conta: " +conta.getTipoConta() + ", Criada em " + conta.getDataCriacao());
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Codigo não existe na tabela");
        }
    }
}

