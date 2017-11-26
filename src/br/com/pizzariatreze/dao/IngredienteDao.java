package br.com.pizzariatreze.dao;

import br.com.pizzariatreze.bd.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.pizzariatreze.dto.IngredienteDto;
import java.util.ArrayList;

public class IngredienteDao {
    
    private ArrayList<IngredienteDto> ingredientes = null;
    private IngredienteDto ingrediente = null;
    private Connection con = null;
    
    public IngredienteDto getById(int id) {
        this.ingrediente = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM ingrediente WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.ingrediente = new IngredienteDto();
                this.ingrediente.setId(rs.getInt("id"));
                this.ingrediente.setNome(rs.getString("nome"));
                this.ingrediente.setDescricao(rs.getString("descricao"));
                this.ingrediente.setQuantidade(rs.getInt("quantidade"));
                this.ingrediente.setValor(rs.getDouble("preco"));
                return this.ingrediente;
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
    
    public ArrayList<IngredienteDto> getByNome(String nome) {
        this.ingredientes.clear();
        this.ingrediente = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM ingrediente WHERE nome LIKE %'?'%");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.ingrediente = new IngredienteDto();
                this.ingrediente.setId(rs.getInt("id"));
                this.ingrediente.setNome(rs.getString("nome"));
                this.ingrediente.setDescricao(rs.getString("descricao"));
                this.ingrediente.setQuantidade(rs.getInt("quantidade"));
                this.ingrediente.setValor(rs.getDouble("preco"));
                this.ingredientes.add(this.ingrediente);
                return this.ingredientes;
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

    public ArrayList<IngredienteDto> getByValor(double valor) {
        this.ingredientes.clear();
        this.ingrediente = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM ingrediente WHERE preco = ?");
            ps.setDouble(1, valor);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.ingrediente = new IngredienteDto();
                this.ingrediente.setId(rs.getInt("id"));
                this.ingrediente.setNome(rs.getString("nome"));
                this.ingrediente.setDescricao(rs.getString("descricao"));
                this.ingrediente.setQuantidade(rs.getInt("quantidade"));
                this.ingrediente.setValor(rs.getDouble("preco"));
                this.ingredientes.add(this.ingrediente);
                return this.ingredientes;
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
    
    public boolean save(IngredienteDto ingrediente) {
        String query = null;
        PreparedStatement ps = null;
        
        if(ingrediente.getId() != 0) {
            IngredienteDto ingredienteBD = this.getById(ingrediente.getId());
            if(ingredienteBD != null) {
                query = "UPDATE ingrediente SET nome = ?, descricao = ?, quantidade = ?, preco = ? WHERE id = ?";
                try {
                    ps = Conexao.getConexao().prepareStatement(query);
                    ps.setString(1, ingrediente.getNome());
                    ps.setString(2, ingrediente.getDescricao());
                    ps.setInt(3, ingrediente.getQuantidade());
                    ps.setDouble(4, ingrediente.getValor());
                    ps.setInt(5, ingrediente.getId());
                    ps.executeUpdate();
                    
                    return true;
                } catch (SQLException ex) {
                    //criar log
                    //"Erro ao atualizar ingrediente: " + ex.getMessage();
                    return false;
                }
            }
        }
        
        try {
            query = ingrediente.getId() != 0 ? "INSERT INTO ingrediente(nome, descricao, quantidade, preco, id) VALUES (?,?,?,?,?)" : "INSERT INTO ingrediente(nome, descricao, quantidade, preco) VALUES (?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, ingrediente.getNome());
            ps.setString(2, ingrediente.getDescricao());
            ps.setInt(3, ingrediente.getQuantidade());
            ps.setDouble(4, ingrediente.getValor());
            if(ingrediente.getId() != 0) {
                ps.setInt(5, ingrediente.getId());
            }
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            //criar log
            //"Erro ao inserir ingrediente: " + ex.getMessage();
            return false;
        }
    }

    public boolean delete(int id) {
        String query = null;
        PreparedStatement ps = null;
        
        query = "DELETE ingrediente WHERE id = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();

            return true;
        } catch (SQLException ex) {
            //criar log
            //"Erro ao excluir ingrediente: " + ex.getMessage();
            return false;
        }
    }
}