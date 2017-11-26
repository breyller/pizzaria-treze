package br.com.pizzariatreze.dao;

import br.com.pizzariatreze.bd.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.pizzariatreze.dto.FuncionarioDto;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {
    
    private ArrayList<FuncionarioDto> funcionarios = new ArrayList<FuncionarioDto>();
    private FuncionarioDto funcionario = null;
    private Connection con = null;
    
    public FuncionarioDto getById(int id) {
        this.funcionario = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM funcionario WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.funcionario = new FuncionarioDto();
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
    
    public ArrayList<FuncionarioDto> getByNome(String nome) {
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
                this.funcionario = new FuncionarioDto();
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

    public ArrayList<FuncionarioDto> getByTelefone(String telefone) {
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
                this.funcionario = new FuncionarioDto();
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
    
    public boolean save(FuncionarioDto funcionario) {
        String result = "Erro ao inserir/atualizar o funcionario";
        String query = null;
        PreparedStatement ps = null;
        
        if(funcionario.getId() != 0) {
            FuncionarioDto funcionarioBD = this.getById(funcionario.getId());
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
                    
                    return true;
                } catch (SQLException ex) {
                    //criar log
                    //"Erro ao atualizar funcionario: " + ex.getMessage();
                    return false;
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
            return true;
        } catch (SQLException ex) {
            //criar log
            //"Erro ao inserir funcionario: " + ex.getMessage();
            return false;
        }
    }
    
    public ArrayList<FuncionarioDto> search(FuncionarioDto funcionario) {

        try{
            con = Conexao.getConexao();
            String sql = "SELECT * FROM funcionario";
            PreparedStatement ps = null;

            List parametros = new ArrayList<>();

            if(funcionario.getId() > 0){
                sql += " WHERE ID = ? ";
                parametros.add(funcionario.getId());
            }else{
                List alt = funcionario.getAlterado();
                String sqlWhere = "";
                int index = 1;

                if(alt.contains("nome")) {
                    sqlWhere += " nome = ? ";
                    parametros.add(funcionario.getNome());
                }

                if(alt.contains("endereco")){
                    sqlWhere += " endereco = ? ";
                    parametros.add(funcionario.getEndereco());
                }

                if(alt.contains("telefone")){
                    sqlWhere += " telefone = ? ";
                    parametros.add(funcionario.getTelefone());
                }

                if(alt.contains("cpf")){
                    sqlWhere += " cpf = ? ";
                    parametros.add(funcionario.getCpf());
                }

                if(alt.contains("salario")){
                    sqlWhere += " salario = ? ";
                    parametros.add(funcionario.getSalario());
                }

                if(alt.contains("cargo")){
                    sqlWhere += " cargo = ? ";
                    parametros.add(funcionario.getCargo());
                }

                if(sqlWhere.length() > 0) sql += " WHERE " + sqlWhere;
            }

            ps = con.prepareStatement(sql);
            int index = 1;

            for(Object i : parametros){
                ps.setObject(index, i);
                index++;
            }

            ResultSet rs = ps.executeQuery();

            if(!rs.next()) {
                return this.funcionarios;
            }

            do {
                this.funcionario = new FuncionarioDto();
                this.funcionario.setId(rs.getInt("id"));
                this.funcionario.setTelefone(rs.getString("telefone"));
                this.funcionario.setNome(rs.getString("nome"));
                this.funcionario.setEndereco(rs.getString("endereco"));
                this.funcionario.setSalario(rs.getDouble("salario"));
                this.funcionario.setCpf(rs.getString("cpf"));
                this.funcionario.setCargo(rs.getString("cargo"));                
                this.funcionarios.add(this.funcionario);
            } while (rs.next());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this.funcionarios;
    }
    
    public boolean delete(int id) {
        String query = null;
        PreparedStatement ps = null;
        
        query = "DELETE funcionario WHERE id = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();

            return true;
        } catch (SQLException ex) {
            //criar log
            //"Erro ao excluir funcionario: " + ex.getMessage();
            return false;
        }
    }    
}
