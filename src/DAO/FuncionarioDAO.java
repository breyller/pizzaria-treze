package DAO;

import BD.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Funcionario;
import java.util.ArrayList;

public class FuncionarioDAO {
    
    private ArrayList<Funcionario> funcionarios = null;
    private Funcionario funcionario = null;
    private Connection con = null;
    
    public Funcionario getById(int id) {
        funcionario = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM funcionario WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEndereco(rs.getString("endereco"));
                return funcionario;
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
    
    public ArrayList<Funcionario> getByNome(String nome) {
        funcionarios.clear();
        funcionario = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM funcionario WHERE nome LIKE %'?'%");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionarios.add(funcionario);
                return funcionarios;
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

    public ArrayList<Funcionario> getByTelefone(String telefone) {
        funcionarios.clear();
        funcionario = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM funcionario WHERE telefone = ?");
            ps.setString(1, telefone);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionarios.add(funcionario);
                return funcionarios;
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
    
    public String save(Funcionario funcionario) {
        String result = "Erro ao inserir/atualizar o funcionario";
        String query = null;
        PreparedStatement ps = null;
        
        if(funcionario.getId() != 0) {
            Funcionario funcionarioBD = this.getById(funcionario.getId());
            if(funcionarioBD != null) {
                query = "UPDATE funcionario SET nome = ?, telefone = ?, cpf = ?, endereco = ? WHERE id = ?";
                try {
                    ps = Conexao.getConexao().prepareStatement(query);
                    ps.setString(1, funcionario.getNome());
                    ps.setString(2, funcionario.getTelefone());
                    ps.setString(3, funcionario.getCpf());
                    ps.setString(4, funcionario.getEndereco());
                    ps.setInt(5, funcionario.getId());
                    ps.executeUpdate();
                    
                    return "Funcionario atualizado com sucesso.";
                } catch (SQLException ex) {
                    return "Erro ao atualizar funcionario: " + ex.getMessage();
                }
            }
        }
        
        try {
            query = funcionario.getId() != 0 ? "INSERT INTO funcionario(nome, telefone, cpf, endereco, id) VALUES (?,?,?,?,?)" : "INSERT INTO funcionario(nome, telefone, cpf, endereco) VALUES (?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getTelefone());
            ps.setString(3, funcionario.getCpf());
            ps.setString(4, funcionario.getEndereco());
            if(funcionario.getId() != 0) {
                ps.setInt(5, funcionario.getId());
            }
            ps.executeUpdate();
            result = "Funcionario criado com sucesso.";
        } catch (SQLException ex) {
            return "Erro ao atualizar funcionario: " + ex.getMessage();
        }
        
        return result;
    }    
}
