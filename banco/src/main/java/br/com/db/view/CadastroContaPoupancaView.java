package br.com.db.view;

import br.com.db.dao.ContaDao;
import br.com.db.model.usuario.ContaPoupanca;

import java.sql.SQLException;

public class CadastroContaPoupancaView {
    public static void main(String[] args) {
        try {
            ContaDao dao = new ContaDao();
            ContaPoupanca contaPoupanca = new ContaPoupanca(0,"0000001", 20, "BRL", new java.sql.Date(new java.util.Date().getTime()), "Poupanca", 2);
            dao.cadastrar(contaPoupanca);
            dao.fecharConexao();
            System.out.println("Conta Poupança cadastrada!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}