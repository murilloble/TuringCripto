package br.com.db.view;

import br.com.db.dao.ContaDao;
import br.com.db.model.usuario.Conta;

import java.sql.SQLException;
import java.util.List;

public class ListagemContasView {

    public static void main(String[] args) {
        try {
            ContaDao dao = new ContaDao();
            List<Conta> contas = dao.listar();
            for (Conta conta : contas) {
                System.out.println(conta.getTipoConta() + " " + conta.getNumeroConta() + ", " + conta.getDataCriacao() + ", " +conta.getTipoMoeda() + ", " + "R$ " + conta.getSaldo());
            }
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}