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
        this.funcionario = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM funcionario WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.funcionario = new Funcionario();
                this.funcionario.setId(rs.getInt("id"));
                this.funcionario.setTelefone(rs.getString("telefone"));
                this.funcionario.setNome(rs.getString("nome"));
                this.funcionario.setEndereco(rs.getString("endereco"));
                this.funcionario.setSalario(rs.getDouble("salario"));
                this.funcionario.setCpf(rs.getString("cpf"));
                this.funcionario.setCargo(rs.getString("cargo"));
                return this.funcionario;
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
        this.funcionarios.clear();
        this.funcionario = null;
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
                this.funcionario = new Funcionario();
                this.funcionario.setId(rs.getInt("id"));
                this.funcionario.setTelefone(rs.getString("telefone"));
                this.funcionario.setNome(rs.getString("nome"));
                this.funcionario.setEndereco(rs.getString("endereco"));
                this.funcionario.setSalario(rs.getDouble("salario"));
                this.funcionario.setCpf(rs.getString("cpf"));
                this.funcionario.setCargo(rs.getString("cargo"));
                this.funcionarios.add(this.funcionario);
                return this.funcionarios;
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
        this.funcionarios.clear();
        this.funcionario = null;
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
                this.funcionario = new Funcionario();
                this.funcionario.setId(rs.getInt("id"));
                this.funcionario.setTelefone(rs.getString("telefone"));
                this.funcionario.setNome(rs.getString("nome"));
                this.funcionario.setEndereco(rs.getString("endereco"));
                this.funcionario.setSalario(rs.getDouble("salario"));
                this.funcionario.setCpf(rs.getString("cpf"));
                this.funcionario.setCargo(rs.getString("cargo"));                
                this.funcionarios.add(this.funcionario);
                return this.funcionarios;
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
                query = "UPDATE funcionario SET nome = ?, telefone = ?, cpf = ?, endereco = ?, cargo = ?, salario = ? WHERE id = ?";
                try {
                    ps = Conexao.getConexao().prepareStatement(query);
                    ps.setString(1, funcionario.getNome());
                    ps.setString(2, funcionario.getTelefone());
                    ps.setString(3, funcionario.getCpf());
                    ps.setString(4, funcionario.getEndereco());
                    ps.setString(5, funcionario.getCargo());
                    ps.setDouble(6, funcionario.getSalario());
                    ps.setInt(7, funcionario.getId());
                    ps.executeUpdate();
                    
                    return "Funcionario atualizado com sucesso.";
                } catch (SQLException ex) {
                    return "Erro ao atualizar funcionario: " + ex.getMessage();
                }
            }
        }
        
        try {
            query = funcionario.getId() != 0 ? "INSERT INTO funcionario(nome, telefone, cpf, endereco, cargo, salario, id) VALUES (?,?,?,?,?,?,?)" : "INSERT INTO funcionario(nome, telefone, cpf, endereco, cargo, salario) VALUES (?,?,?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getTelefone());
            ps.setString(3, funcionario.getCpf());
            ps.setString(4, funcionario.getEndereco());
            ps.setString(5, funcionario.getCargo());
            ps.setDouble(6, funcionario.getSalario());
            if(funcionario.getId() != 0) {
                ps.setInt(7, funcionario.getId());
            }
            ps.executeUpdate();
            result = "Funcionario criado com sucesso.";
        } catch (SQLException ex) {
            return "Erro ao inserir funcionario: " + ex.getMessage();
        }
        
        return result;
    }    
}
