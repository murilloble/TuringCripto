package br.com.db.view;
import br.com.db.dao.ContaDao;
import br.com.db.exception.EntidadeNaoEncontradaException;

import java.sql.SQLException;

public class RemocaoContaView {
    public static void main(String[] args) {
        try {
            ContaDao dao = new ContaDao();
            dao.remover(13);
            dao.fecharConexao();
            System.out.println("Conta Removido!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Conta não encontrada");
        }
    }
}