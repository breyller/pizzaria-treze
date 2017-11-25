package br.com.pizzariatreze.dao;

import br.com.pizzariatreze.bd.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.pizzariatreze.dto.ClienteDto;
import java.util.ArrayList;

public class ClienteDao {
    
    private ArrayList<ClienteDto> clientes = null;
    private ClienteDto cliente = null;
    private Connection con = null;
    
    public ClienteDto getById(int id) {
        this.cliente = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM cliente WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.cliente = new ClienteDto();
                this.cliente.setId(rs.getInt("id"));
                this.cliente.setTelefone(rs.getString("telefone"));
                this.cliente.setNome(rs.getString("nome"));
                this.cliente.setEndereco(rs.getString("endereco"));
                return this.cliente;
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
    
    public ArrayList<ClienteDto> getByNome(String nome) {
        this.clientes.clear();
        this.cliente = null;
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
                this.cliente = new ClienteDto();
                this.cliente.setId(rs.getInt("id"));
                this.cliente.setTelefone(rs.getString("telefone"));
                this.cliente.setNome(rs.getString("nome"));
                this.cliente.setEndereco(rs.getString("endereco"));
                this.clientes.add(this.cliente);
                return this.clientes;
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

    public ArrayList<ClienteDto> getByTelefone(String telefone) {
        this.clientes.clear();
        this.cliente = null;
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
                this.cliente = new ClienteDto();
                this.cliente.setId(rs.getInt("id"));
                this.cliente.setTelefone(rs.getString("telefone"));
                this.cliente.setNome(rs.getString("nome"));
                this.cliente.setEndereco(rs.getString("endereco"));
                this.clientes.add(this.cliente);
                return this.clientes;
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
    
    public String save(ClienteDto cliente) {
        String result = "Erro ao inserir/atualizar o cliente";
        String query = null;
        PreparedStatement ps = null;
        
        if(cliente.getId() != 0) {
            ClienteDto clienteBD = this.getById(cliente.getId());
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
            return "Erro ao inserir cliente: " + ex.getMessage();
        }
        
        return result;
    }
}
