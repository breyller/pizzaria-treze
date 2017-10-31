package DAO;

import BD.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;
import java.util.ArrayList;

public class ClienteDAO {
    private ArrayList<Cliente> clientes = null;
    private Cliente cliente = null;
    private Connection con = null;
    
    public Cliente getById(int id) {
        cliente = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
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
    
    public ArrayList<Cliente> getByNome(String nome) {
        clientes.clear();
        cliente = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM cliente WHERE nome LIKE %'?'%");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                clientes.add(cliente);
                return clientes;
            } while (rs.next());
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

    public ArrayList<Cliente> getByTelefone(String telefone) {
        clientes.clear();
        cliente = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM cliente WHERE telefone = ?");
            ps.setString(1, telefone);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                clientes.add(cliente);
                return clientes;
            } while (rs.next());
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
    
    public String save(Cliente cliente) {
        String result = "Erro ao inserir/atualizar o cliente";
        String query = null;
        PreparedStatement ps = null;
        
        if(cliente.getId() != 0) {
            Cliente clienteBD = this.getById(cliente.getId());
            if(clienteBD != null) {
                query = "UPDATE cliente SET nome = ?, telefone = ?, cpf = ?, endereco = ? WHERE id = ?";
                try {
                    ps = Conexao.getConexao().prepareStatement(query);
                    ps.setString(1, cliente.getNome());
                    ps.setString(2, cliente.getTelefone());
                    ps.setString(3, cliente.getCpf());
                    ps.setString(4, cliente.getEndereco());
                    ps.setInt(5, cliente.getId());
                    ps.executeUpdate();
                    
                    return "Cliente atualizado com sucesso.";
                } catch (SQLException ex) {
                    return "Erro ao atualizar cliente: " + ex.getMessage();
                }
            }
        }
        
        try {
            query = cliente.getId() != 0 ? "INSERT INTO cliente(nome, telefone, cpf, endereco, id) VALUES (?,?,?,?,?)" : "INSERT INTO cliente(nome, telefone, cpf, endereco) VALUES (?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getEndereco());
            if(cliente.getId() != 0) {
                ps.setInt(5, cliente.getId());
            }
            ps.executeUpdate();
            result = "Cliente criado com sucesso.";
        } catch (SQLException ex) {
            return "Erro ao atualizar cliente: " + ex.getMessage();
        }
        
        return result;
    }
}
