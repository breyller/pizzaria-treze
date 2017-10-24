package DAO;

import BD.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;
import java.util.ArrayList;
import javax.sql.DataSource;

public class ClienteDAO {
    private DataSource ds;
    private ArrayList<Cliente> clientes = null;
    private Cliente cliente = null;
    private Connection con = null;
    
    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }

    public Cliente getById(int id) {
        PreparedStatement ps = null;
        
        try {
            con = ds.getConnection();
            ps = con.prepareStatement("SELECT * FROM cliente WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                return cliente;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
