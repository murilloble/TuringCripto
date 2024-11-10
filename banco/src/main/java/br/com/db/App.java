package br.com.db;

import br.com.db.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
public class App
{
    public static void main( String[] args )
    {
        try {
            Connection conexao = ConnectionFactory.getConnection();
            System.out.println("Login realizado com sucesso");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}