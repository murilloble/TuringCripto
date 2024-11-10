package br.com.db.view;

import br.com.db.dao.ContaDao;
import br.com.db.model.usuario.ContaInvestimento;

import java.sql.SQLException;

public class CadastroContaInvestimentoView {
    public static void main(String[] args) {
        try {
            ContaDao dao = new ContaDao();
            ContaInvestimento contaInvestimento = new ContaInvestimento(0,"0000001", 20, "BRL", new java.sql.Date(new java.util.Date().getTime()), "Investimento", 2);
            dao.cadastrar(contaInvestimento);
            dao.fecharConexao();
            System.out.println("Conta de Investimento cadastrado!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}