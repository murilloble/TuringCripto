package br.com.db.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL =  "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String LOGIN = "system";
    private static final String PASSWORD = "system";

    //metodo para obter uma conexao com o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,LOGIN, PASSWORD);
    }

}
