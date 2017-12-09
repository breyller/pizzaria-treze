package br.com.pizzariatreze.dao;

import br.com.pizzariatreze.bd.Conexao;
import br.com.pizzariatreze.dto.ProdutoDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    private ArrayList<ProdutoDto> produtos;
    private ProdutoDto produto = null;
    private Connection con = null;

    public ProdutoDao() {
        this.produtos = new ArrayList<>();
    }
    
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
    
    public boolean save(ProdutoDto produto) {
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
                    ps.setString(3, produto.getDescricao());
                    ingredientes = "";
                    if(produto.getComposicao() != null) {
                        ingredientes = String.valueOf(produto.getComposicao().get(0).getId());
                        for(int i = 1; i < produto.getComposicao().size(); i++) {
                            ingredientes = ingredientes + "," + String.valueOf(produto.getComposicao().get(i).getId());
                        }
                    }else if(!produto.getComposicaoString().equals("")){
                        ingredientes = produto.getComposicaoString();
                    }
                    ps.setString(4, ingredientes);
                    ps.setInt(5, produto.getId());
                    ps.executeUpdate();
                    
                    return true;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        }
        
        try {
            query = "INSERT INTO produto(nome, preco, descricao, composicao) VALUES (?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setString(3, produto.getDescricao());
            
            ingredientes = "";
            if(produto.getComposicao().size() > 0) {
                ingredientes = String.valueOf(produto.getComposicao().get(0).getId());
                for(int i = 1; i < produto.getComposicao().size(); i++) {
                    ingredientes = ingredientes + "," + String.valueOf(produto.getComposicao().get(i).getId());
                }
            }else if(!produto.getComposicaoString().equals("")){
                ingredientes = produto.getComposicaoString();
            }
            
            ps.setString(4, ingredientes);
            
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(int id) {
        String query = null;
        PreparedStatement ps = null;
        
        query = "DELETE produto WHERE id = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();

            return true;
        } catch (SQLException ex) {
            //criar log
            //"Erro ao excluir produto: " + ex.getMessage();
            return false;
        }
    }

    public Integer pesquisarPizza(String composicao) {
        //this.produto = null;
        //this.produtos.clear();
        PreparedStatement ps = null;
        String ingredientes = null;
        //String[] ingredientesSplit = null;
        IngredienteDao ingredienteDao = new IngredienteDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT id FROM produto WHERE composicao = ?");
            ps.setString(1, composicao);
            ResultSet rs = ps.executeQuery();
            
             if(!rs.next()) {
                return 0;
            }
             
            return rs.getInt("id");

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
    
    public List<Object> search(ProdutoDto produto) {
 
        List<Object> produtosObj = new ArrayList<>();
        
        try{
            con = Conexao.getConexao();
            String sql = "SELECT * FROM produto";
            PreparedStatement ps = null;
        
            List parametros = new ArrayList<>();

            if(produto.getId() > 0){
                sql += " WHERE ID = ? ";
                parametros.add(produto.getId());
            }else{
                List alt = produto.getAlterado();
                String sqlWhere = "";
                int index = 1;

                if(alt.contains("nome")) {
                    sqlWhere += " AND nome = ? ";
                    parametros.add(produto.getNome());
                }

                if(alt.contains("descricao")){
                    sqlWhere += " AND descricao = ? ";
                    parametros.add(produto.getDescricao());
                }

                if(alt.contains("preco")){
                    sqlWhere += " AND preco = ? ";
                    parametros.add(produto.getPreco());
                }

                if(alt.contains("composicao")){
                    sqlWhere += " AND composicao = ? ";
                    parametros.add(produto.getComposicaoString());
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
                    this.produto = new ProdutoDto();
                    this.produto.setId(rs.getInt("id"));
                    this.produto.setNome(rs.getString("nome"));
                    this.produto.setDescricao(rs.getString("descricao"));
                    this.produto.setPreco(rs.getInt("preco"));
                    this.produto.setComposicao(rs.getString("composicao"));
                    this.produtos.add(this.produto);
                    produtosObj.add((Object)this.produto);
                } while (rs.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return produtosObj;
    }
}
