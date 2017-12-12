package br.com.pizzariatreze.dao;

import br.com.pizzariatreze.bd.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.pizzariatreze.dto.ClienteDto;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    
    private ArrayList<ClienteDto> clientes = new ArrayList<>();
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
                this.cliente.setCpf(rs.getString("cpf"));
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
    
    public boolean save(ClienteDto cliente) {
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
                    
                    return true;
                } catch (SQLException ex) {
                    //criar log
                    //"Erro ao atualizar cliente: " + ex.getMessage();
                    return false;
                }
            }
        }
        
        try {
            query = "INSERT INTO cliente(nome, telefone, cpf, endereco) VALUES (?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getEndereco());
    
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            //criar log
            //"Erro ao inserir cliente: " + ex.getMessage();
            return false;
        }        
    }

    public List<Object> search(ClienteDto cliente) {
 
        List<Object> clientesObj = new ArrayList<>();
        
        try{
            con = Conexao.getConexao();
            String sql = "SELECT * FROM cliente";
            PreparedStatement ps = null;
        
            List parametros = new ArrayList<>();

            if(cliente.getId() > 0){
                sql += " WHERE ID = ? ";
                parametros.add(cliente.getId());
            }else{
                List alt = cliente.getAlterado();
                String sqlWhere = "";
                int index = 1;

                if(alt.contains("nome")) {
                    sqlWhere += " AND nome = ? ";
                    parametros.add(cliente.getNome());
                }

                if(alt.contains("endereco")){
                    sqlWhere += " AND endereco = ? ";
                    parametros.add(cliente.getEndereco());
                }

                if(alt.contains("telefone")){
                    sqlWhere += " AND telefone = ? ";
                    parametros.add(cliente.getTelefone());
                }

                if(alt.contains("cpf")){
                    sqlWhere += " AND cpf = ? ";
                    parametros.add(cliente.getCpf());
                }

                if(sqlWhere.length() > 0) sql += " WHERE 1=1 " + sqlWhere;
            }

            ps = con.prepareStatement(sql);
            int index = 1;

            for(Object i : parametros){
                ps.setObject(index, i);
                index++;
            }

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                do {
                    this.cliente = new ClienteDto();
                    this.cliente.setId(rs.getInt("id"));
                    this.cliente.setTelefone(rs.getString("telefone"));
                    this.cliente.setNome(rs.getString("nome"));
                    this.cliente.setEndereco(rs.getString("endereco"));
                    this.cliente.setCpf(rs.getString("cpf"));
                    this.clientes.add(this.cliente);
                    clientesObj.add((Object)this.cliente);
                } while (rs.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return clientesObj;
    }
    
    public boolean delete(int id) {
        String query = null;
        PreparedStatement ps = null;
        
        query = "DELETE FROM cliente WHERE id = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
            
            if(ps.getUpdateCount() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
