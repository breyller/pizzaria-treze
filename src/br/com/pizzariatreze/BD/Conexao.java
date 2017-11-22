package br.com.pizzariatreze.BD;

import java.sql.*;

public class Conexao {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    private static final String SERVIDOR = "localhost";
    private static final String DB = "Pizzaria";
    private static final String STR_CONEXAO = "jdbc:mysql://"+SERVIDOR+":3306/"+DB;
    
    public static Connection getConexao() throws SQLException {
	Connection con = null;
	try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(STR_CONEXAO, USUARIO, SENHA);
            return con;
	} catch (ClassNotFoundException e) {
            String errorMsg = "Driver nao encontrado.";
            throw new SQLException(errorMsg, e);
	} catch (SQLException e) {
            String errorMsg = "Erro ao obter a conexao.";
            throw new SQLException(errorMsg, e);
	}
    }	

    public static void fecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
