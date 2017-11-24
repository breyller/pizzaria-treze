package br.com.pizzariatreze.DAO;

import br.com.pizzariatreze.BD.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.pizzariatreze.DTO.GerenteDTO;

public class GerenteDAO {
    
    private ArrayList<GerenteDTO> gerentes = null;
    private GerenteDTO gerente = null;
    private Connection con = null;
    
    public GerenteDTO getById(int id) {
        this.gerente = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM funcionario WHERE id = ? AND cargo = 'Gerente'");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.gerente = new GerenteDTO();
                this.gerente.setId(rs.getInt("id"));
                this.gerente.setTelefone(rs.getString("telefone"));
                this.gerente.setNome(rs.getString("nome"));
                this.gerente.setEndereco(rs.getString("endereco"));
                this.gerente.setSalario(rs.getDouble("salario"));
                this.gerente.setCpf(rs.getString("cpf"));
                this.gerente.setCargo(rs.getString("cargo"));
                return this.gerente;
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
    
    public ArrayList<GerenteDTO> getByNome(String nome) {
        this.gerentes.clear();
        this.gerente = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM funcionario WHERE nome LIKE %'?'% AND cargo = 'Gerente'");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.gerente = new GerenteDTO();
                this.gerente.setId(rs.getInt("id"));
                this.gerente.setTelefone(rs.getString("telefone"));
                this.gerente.setNome(rs.getString("nome"));
                this.gerente.setEndereco(rs.getString("endereco"));
                this.gerente.setSalario(rs.getDouble("salario"));
                this.gerente.setCpf(rs.getString("cpf"));
                this.gerente.setCargo(rs.getString("cargo"));
                this.gerentes.add(this.gerente);
                return this.gerentes;
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

    public ArrayList<GerenteDTO> getByTelefone(String telefone) {
        this.gerentes.clear();
        this.gerente = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM funcionario WHERE telefone = ? AND cargo = 'Gerente'");
            ps.setString(1, telefone);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.gerente = new GerenteDTO();
                this.gerente.setId(rs.getInt("id"));
                this.gerente.setTelefone(rs.getString("telefone"));
                this.gerente.setNome(rs.getString("nome"));
                this.gerente.setEndereco(rs.getString("endereco"));
                this.gerente.setSalario(rs.getDouble("salario"));
                this.gerente.setCpf(rs.getString("cpf"));
                this.gerente.setCargo(rs.getString("cargo"));
                this.gerentes.add(this.gerente);
                return this.gerentes;
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
    
    public String save(GerenteDTO gerente) {
        String result = "Erro ao inserir/atualizar o gerente";
        String query = null;
        PreparedStatement ps = null;
        
        if(gerente.getId() != 0) {
            GerenteDTO gerenteBD = this.getById(gerente.getId());
            if(gerenteBD != null) {
                query = "UPDATE funcionario SET nome = ?, telefone = ?, cpf = ?, endereco = ?, cargo = ?, salario = ? WHERE id = ? AND cargo = 'Gerente'";
                try {
                    ps = Conexao.getConexao().prepareStatement(query);
                    ps.setString(1, gerente.getNome());
                    ps.setString(2, gerente.getTelefone());
                    ps.setString(3, gerente.getCpf());
                    ps.setString(4, gerente.getEndereco());
                    ps.setString(5, gerente.getCargo());
                    ps.setDouble(6, gerente.getSalario());
                    ps.setInt(7, gerente.getId());
                    ps.executeUpdate();
                    
                    return "Gerente atualizado com sucesso.";
                } catch (SQLException ex) {
                    return "Erro ao atualizar gerente: " + ex.getMessage();
                }
            }
        }
        
        try {
            query = gerente.getId() != 0 ? "INSERT INTO funcionario(nome, telefone, cpf, endereco, cargo, salario, id) VALUES (?,?,?,?,?,?,?)" : "INSERT INTO funcionario(nome, telefone, cpf, endereco, cargo, salario) VALUES (?,?,?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, gerente.getNome());
            ps.setString(2, gerente.getTelefone());
            ps.setString(3, gerente.getCpf());
            ps.setString(4, gerente.getEndereco());
            ps.setString(5, gerente.getCargo());
            ps.setDouble(6, gerente.getSalario());
            if(gerente.getId() != 0) {
                ps.setInt(7, gerente.getId());
            }
            ps.executeUpdate();
            result = "Gerente criado com sucesso.";
        } catch (SQLException ex) {
            return "Erro ao inserir gerente: " + ex.getMessage();
        }
        
        return result;
    }    
}
