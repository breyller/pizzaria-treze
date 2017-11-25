package br.com.pizzariatreze.dao;

import br.com.pizzariatreze.bd.Conexao;
import br.com.pizzariatreze.dto.ProdutoDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDao {

    private ArrayList<ProdutoDto> produtos = null;
    private ProdutoDto produto = null;
    private Connection con = null;
    
    public ProdutoDto getById(int id) {
        this.produto = null;
        PreparedStatement ps = null;
        String ingredientes = null;
        String[] ingredientesSplit = null;
        IngredienteDao ingredienteDao = new IngredienteDao();

        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM produto WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.produto = new ProdutoDto();
                this.produto.setId(rs.getInt("id"));
                this.produto.setNome(rs.getString("nome"));
                this.produto.setPreco(rs.getDouble("preco"));
                this.produto.setDescricao(rs.getString("descricao"));
                ingredientes = rs.getString("composicao");
                ingredientesSplit = ingredientes.split(",");
                for (int i = 0; i < ingredientesSplit.length; i++) {
                    this.produto.setComposicao(ingredienteDao.getById(Integer.parseInt(ingredientesSplit[i])));
                }
                return this.produto;
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
    
    public ArrayList<ProdutoDto> getByNome(String nome) {
        this.produto = null;
        this.produtos.clear();
        PreparedStatement ps = null;
        String ingredientes = null;
        String[] ingredientesSplit = null;
        IngredienteDao ingredienteDao = new IngredienteDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM produto WHERE nome LIKE %'?'%");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.produto = new ProdutoDto();
                this.produto.setId(rs.getInt("id"));
                this.produto.setNome(rs.getString("nome"));
                this.produto.setPreco(rs.getDouble("preco"));
                this.produto.setDescricao(rs.getString("descricao"));
                ingredientes = rs.getString("composicao");
                ingredientesSplit = ingredientes.split(",");
                for (int i = 0; i < ingredientesSplit.length; i++) {
                    this.produto.setComposicao(ingredienteDao.getById(Integer.parseInt(ingredientesSplit[i])));
                }
                this.produtos.add(this.produto);
                return this.produtos;
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

    public ArrayList<ProdutoDto> getByDescricao(String descricao) {
        this.produto = null;
        this.produtos.clear();
        PreparedStatement ps = null;
        String ingredientes = null;
        String[] ingredientesSplit = null;
        IngredienteDao ingredienteDao = new IngredienteDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM produto WHERE descricao LIKE %'?'%");
            ps.setString(1, descricao);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.produto = new ProdutoDto();
                this.produto.setId(rs.getInt("id"));
                this.produto.setNome(rs.getString("nome"));
                this.produto.setPreco(rs.getDouble("preco"));
                this.produto.setDescricao(rs.getString("descricao"));
                ingredientes = rs.getString("composicao");
                ingredientesSplit = ingredientes.split(",");
                for (int i = 0; i < ingredientesSplit.length; i++) {
                    this.produto.setComposicao(ingredienteDao.getById(Integer.parseInt(ingredientesSplit[i])));
                }
                this.produtos.add(this.produto);
                return this.produtos;
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
    
    public ArrayList<ProdutoDto> getByIngrediente(int ingredienteId) {
        this.produto = null;
        this.produtos.clear();
        PreparedStatement ps = null;
        String ingredientes = null;
        String[] ingredientesSplit = null;
        IngredienteDao ingredienteDao = new IngredienteDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM produto WHERE composicao LIKE %',?'% OR descricao LIKE %'?,'%");
            ps.setString(1, String.valueOf(ingredienteId));
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.produto = new ProdutoDto();
                this.produto.setId(rs.getInt("id"));
                this.produto.setNome(rs.getString("nome"));
                this.produto.setPreco(rs.getDouble("preco"));
                this.produto.setDescricao(rs.getString("descricao"));
                ingredientes = rs.getString("composicao");
                ingredientesSplit = ingredientes.split(",");
                for (int i = 0; i < ingredientesSplit.length; i++) {
                    this.produto.setComposicao(ingredienteDao.getById(Integer.parseInt(ingredientesSplit[i])));
                }
                this.produtos.add(this.produto);
                return this.produtos;
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
    
    public String save(ProdutoDto produto) {
        String result = "Erro ao inserir/atualizar o cliente";
        String query = null;
        PreparedStatement ps = null;
        String ingredientes = null;
        
        if(produto.getId() != 0) {
            ProdutoDto produtoBD = this.getById(produto.getId());
            if(produtoBD != null) {
                query = "UPDATE produto SET nome = ?, preco = ?, descricao = ?, composicao = ? WHERE id = ?";
                try {
                    ps = Conexao.getConexao().prepareStatement(query);
                    ps.setString(1, produto.getNome());
                    ps.setDouble(2, produto.getPreco());
                    ingredientes = String.valueOf(produto.getComposicao().get(0).getId());
                    for(int i = 1; i < produto.getComposicao().size(); i++) {
                        ingredientes = ingredientes + "," + String.valueOf(produto.getComposicao().get(i).getId());
                    }
                    ps.setString(3, ingredientes);
                    ps.setInt(4, produto.getId());
                    ps.executeUpdate();
                    
                    return "Produto atualizado com sucesso.";
                } catch (SQLException ex) {
                    return "Erro ao atualizar produto: " + ex.getMessage();
                }
            }
        }
        
        try {
            query = produto.getId() != 0 ? "INSERT INTO produto(nome, preco, descricao, composicao, id) VALUES (?,?,?,?,?)" : "INSERT INTO cliente(nome, preco, descricao, composicao) VALUES (?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setString(3, produto.getDescricao());
            ingredientes = String.valueOf(produto.getComposicao().get(0).getId());
            for(int i = 1; i < produto.getComposicao().size(); i++) {
                ingredientes = ingredientes + "," + String.valueOf(produto.getComposicao().get(i).getId());
            }
            ps.setString(4, ingredientes);
            if(produto.getId() != 0) {
                ps.setInt(5, produto.getId());
            }
            ps.executeUpdate();
            result = "Produto criado com sucesso.";
        } catch (SQLException ex) {
            return "Erro ao inserir produto: " + ex.getMessage();
        }
        
        return result;
    }
}
