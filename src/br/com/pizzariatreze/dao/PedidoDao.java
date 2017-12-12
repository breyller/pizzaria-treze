package br.com.pizzariatreze.dao;

import br.com.pizzariatreze.bd.Conexao;
import br.com.pizzariatreze.dao.ClienteDao;
import br.com.pizzariatreze.dao.FuncionarioDao;
import br.com.pizzariatreze.dto.PedidoDto;
import br.com.pizzariatreze.dto.ProdutoDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

    private ArrayList<PedidoDto> pedidos = null;
    private PedidoDto pedido = null;
    private Connection con = null;
    
    public PedidoDto getById(int id) {
        this.pedido = null;
        PreparedStatement ps = null;
        ClienteDao cdao = new ClienteDao();
        FuncionarioDao fdao = new FuncionarioDao();
        ProdutoDao pdao = new ProdutoDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.pedido = new PedidoDto();
                this.pedido.setId(rs.getInt("id"));
                this.pedido.setData(rs.getString("data"));
                this.pedido.setStatus(rs.getInt("status"));
                this.pedido.setDescricao(rs.getString("descricao"));
                this.pedido.setTipo(rs.getInt("tipo"));
                this.pedido.setPreco(rs.getDouble("preco"));
                this.pedido.setCliente(cdao.getById(rs.getInt("id_cliente")));
                this.pedido.setFuncionario(fdao.getById(rs.getInt("id_funcionario")));
                String produtos = rs.getString("composicao");
                this.pedido.setComposicao(produtos);
                if(!produtos.equals("") && !produtos.equals(null)) {
                    String[] produtosSplit = produtos.split(",");
                    for (int i = 0; i < produtosSplit.length; i++) {
                        String[] qtdSplit = produtosSplit[i].split("|");
                        int idProduto = Integer.parseInt(qtdSplit[0]);
                        ProdutoDto addProduto = pdao.getById(idProduto);
                        if(addProduto != null) {
                            this.pedido.setComposicao(addProduto);
                        }
                    }
                }
                return this.pedido;
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
    
    public ArrayList<PedidoDto> getByData(String data) {
        this.pedido = null;
        this.pedidos.clear();
        PreparedStatement ps = null;
        String produtos = null;
        String[] produtosSplit = null;
        ClienteDao clienteDao = new ClienteDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        ProdutoDao produtoDao = new ProdutoDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE data = ?");
            ps.setString(1, data);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.pedido = new PedidoDto();
                this.pedido.setId(rs.getInt("id"));
                this.pedido.setData(rs.getString("data"));
                this.pedido.setStatus(rs.getInt("status"));
                this.pedido.setDescricao(rs.getString("descricao"));
                this.pedido.setTipo(rs.getInt("tipo"));
                this.pedido.setPreco(rs.getDouble("preco"));
                this.pedido.setCliente(clienteDao.getById(rs.getInt("id_cliente")));
                this.pedido.setFuncionario(funcionarioDao.getById(rs.getInt("id_funcionario")));
                produtos = rs.getString("composicao");
                produtosSplit = produtos.split(",");
                for (int i = 0; i < produtosSplit.length; i++) {
                    this.pedido.setComposicao(produtoDao.getById(Integer.parseInt(produtosSplit[i])));
                }
                this.pedidos.add(this.pedido);
                return this.pedidos;
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

    public ArrayList<PedidoDto> getByStatus(int status) {
        this.pedido = null;
        this.pedidos.clear();
        PreparedStatement ps = null;
        String produtos = null;
        String[] produtosSplit = null;
        ClienteDao clienteDao = new ClienteDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        ProdutoDao produtoDao = new ProdutoDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE status = ?");
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.pedido = new PedidoDto();
                this.pedido.setId(rs.getInt("id"));
                this.pedido.setData(rs.getString("data"));
                this.pedido.setStatus(rs.getInt("status"));
                this.pedido.setDescricao(rs.getString("descricao"));
                this.pedido.setTipo(rs.getInt("tipo"));
                this.pedido.setPreco(rs.getDouble("preco"));
                this.pedido.setCliente(clienteDao.getById(rs.getInt("id_cliente")));
                this.pedido.setFuncionario(funcionarioDao.getById(rs.getInt("id_funcionario")));
                produtos = rs.getString("composicao");
                produtosSplit = produtos.split(",");
                for (int i = 0; i < produtosSplit.length; i++) {
                    this.pedido.setComposicao(produtoDao.getById(Integer.parseInt(produtosSplit[i])));
                }
                this.pedidos.add(this.pedido);
                return this.pedidos;
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

    public ArrayList<PedidoDto> getByTipo(int tipo) {
        this.pedido = null;
        this.pedidos.clear();
        PreparedStatement ps = null;
        String produtos = null;
        String[] produtosSplit = null;
        ClienteDao clienteDao = new ClienteDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        ProdutoDao produtoDao = new ProdutoDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE tipo = ?");
            ps.setInt(1, tipo);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.pedido = new PedidoDto();
                this.pedido.setId(rs.getInt("id"));
                this.pedido.setData(rs.getString("data"));
                this.pedido.setStatus(rs.getInt("status"));
                this.pedido.setDescricao(rs.getString("descricao"));
                this.pedido.setTipo(rs.getInt("tipo"));
                this.pedido.setPreco(rs.getDouble("preco"));
                this.pedido.setCliente(clienteDao.getById(rs.getInt("id_cliente")));
                this.pedido.setFuncionario(funcionarioDao.getById(rs.getInt("id_funcionario")));
                produtos = rs.getString("composicao");
                produtosSplit = produtos.split(",");
                for (int i = 0; i < produtosSplit.length; i++) {
                    this.pedido.setComposicao(produtoDao.getById(Integer.parseInt(produtosSplit[i])));
                }
                this.pedidos.add(this.pedido);
                return this.pedidos;
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

    public ArrayList<PedidoDto> getByCliente(int idCliente) {
        this.pedido = null;
        this.pedidos.clear();
        PreparedStatement ps = null;
        String produtos = null;
        String[] produtosSplit = null;
        ClienteDao clienteDao = new ClienteDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        ProdutoDao produtoDao = new ProdutoDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE id_cliente = ?");
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.pedido = new PedidoDto();
                this.pedido.setId(rs.getInt("id"));
                this.pedido.setData(rs.getString("data"));
                this.pedido.setStatus(rs.getInt("status"));
                this.pedido.setDescricao(rs.getString("descricao"));
                this.pedido.setTipo(rs.getInt("tipo"));
                this.pedido.setPreco(rs.getDouble("preco"));
                this.pedido.setCliente(clienteDao.getById(rs.getInt("id_cliente")));
                this.pedido.setFuncionario(funcionarioDao.getById(rs.getInt("id_funcionario")));
                produtos = rs.getString("composicao");
                produtosSplit = produtos.split(",");
                for (int i = 0; i < produtosSplit.length; i++) {
                    this.pedido.setComposicao(produtoDao.getById(Integer.parseInt(produtosSplit[i])));
                }
                this.pedidos.add(this.pedido);
                return this.pedidos;
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
    
    public ArrayList<PedidoDto> getByFuncionario(int idFuncionario) {
        this.pedido = null;
        this.pedidos.clear();
        PreparedStatement ps = null;
        String produtos = null;
        String[] produtosSplit = null;
        ClienteDao clienteDao = new ClienteDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        ProdutoDao produtoDao = new ProdutoDao();        

        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM pedido WHERE id_funcionario = ?");
            ps.setInt(1, idFuncionario);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.pedido = new PedidoDto();
                this.pedido.setId(rs.getInt("id"));
                this.pedido.setData(rs.getString("data"));
                this.pedido.setStatus(rs.getInt("status"));
                this.pedido.setDescricao(rs.getString("descricao"));
                this.pedido.setTipo(rs.getInt("tipo"));
                this.pedido.setPreco(rs.getDouble("preco"));
                this.pedido.setCliente(clienteDao.getById(rs.getInt("id_cliente")));
                this.pedido.setFuncionario(funcionarioDao.getById(rs.getInt("id_funcionario")));
                produtos = rs.getString("composicao");
                produtosSplit = produtos.split(",");
                for (int i = 0; i < produtosSplit.length; i++) {
                    this.pedido.setComposicao(produtoDao.getById(Integer.parseInt(produtosSplit[i])));
                }
                this.pedidos.add(this.pedido);
                return this.pedidos;
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
    
    public boolean save(PedidoDto pedido) {
        String query = null;
        PreparedStatement ps = null;
        String produtos = null;
        if(pedido.getId() != 0) {
            PedidoDto pedidoBD = this.getById(pedido.getId());
            if(pedidoBD != null) {
                query = "UPDATE pedido SET data = ?, status = ?, descricao = ?, tipo = ?, preco = ?, id_cliente = ?, id_funcionario = ? WHERE id = ?";
                try {
                    ps = Conexao.getConexao().prepareStatement(query);
                    ps.setString(1, pedidoBD.getData());
                    ps.setInt(2, pedido.getStatus());
                    ps.setString(3, pedidoBD.getDescricao());
                    ps.setInt(4, pedidoBD.getTipo());
                    ps.setDouble(5, pedidoBD.getPreco());
                    ps.setInt(6, pedidoBD.getCliente().getId());
                    ps.setInt(7, pedidoBD.getFuncionario().getId());
                    ps.setInt(8, pedidoBD.getId());
                    ps.executeUpdate();
                    
                    return true;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        }
        
        try {
            query = "INSERT INTO pedido(data, status, descricao, tipo, preco, id_cliente, id_funcionario, composicao) VALUES (?,?,?,?,?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, pedido.getData());
            ps.setInt(2, pedido.getStatus());
            ps.setString(3, pedido.getDescricao());
            ps.setInt(4, pedido.getTipo());
            ps.setDouble(5, pedido.getPreco());
            ps.setInt(6, pedido.getCliente().getId());
            ps.setInt(7, pedido.getFuncionario().getId());
            if(!pedido.getComposicao().isEmpty()) {
                produtos = String.valueOf(pedido.getComposicao().get(0).getId());
                for(int i = 1; i < pedido.getComposicao().size(); i++) {
                    produtos = produtos + "," + String.valueOf(pedido.getComposicao().get(i).getId());
                }
            }else if(!pedido.getComposicaoString().equals("")){
                produtos = pedido.getComposicaoString();
            }
            
            ps.setString(8, produtos);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Object> search(PedidoDto pedido) {
        this.pedido = null;
        List<Object> pedidosObj = new ArrayList<>();
        ClienteDao cdao = new ClienteDao();
        FuncionarioDao fdao = new FuncionarioDao();
        ProdutoDao pdao = new ProdutoDao();
        
        try{
            con = Conexao.getConexao();
            String sql = "SELECT * FROM pedido";
            PreparedStatement ps = null;
        
            List parametros = new ArrayList<>();

            if (pedido.getId() > 0) {
                sql += " WHERE id = ? ";
                parametros.add(pedido.getId());
            } else {
                List alt = pedido.getAlterado();
                String sqlWhere = "";
                int index = 1;

                if(alt.contains("id_cliente")) {
                    sqlWhere += " AND id_cliente = ? ";
                    parametros.add(pedido.getCliente());
                }

                if(alt.contains("id_funcionario")){
                    sqlWhere += " AND id_funcionario = ? ";
                    parametros.add(pedido.getFuncionario());
                }

                if(alt.contains("preco")){
                    sqlWhere += " AND preco = ? ";
                    parametros.add(pedido.getPreco());
                }

                if(alt.contains("status")){
                    sqlWhere += " AND status = ? ";
                    parametros.add(pedido.getStatus());
                }

                if(alt.contains("tipo")){
                    sqlWhere += " AND tipo = ? ";
                    parametros.add(pedido.getTipo());
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
                    this.pedido = new PedidoDto();
                    this.pedido.setId(rs.getInt("id"));
                    this.pedido.setData(rs.getString("data"));
                    this.pedido.setStatus(rs.getInt("status"));
                    this.pedido.setDescricao(rs.getString("descricao"));
                    this.pedido.setTipo(rs.getInt("tipo"));
                    this.pedido.setPreco(rs.getDouble("preco"));
                    this.pedido.setCliente(cdao.getById(rs.getInt("id_cliente")));
                    this.pedido.setFuncionario(fdao.getById(rs.getInt("id_funcionario")));
                    String produtos = rs.getString("composicao");
                    this.pedido.setComposicao(produtos);
                    if(!produtos.equals("") && !produtos.equals(null)) {
                        String[] produtosSplit = produtos.split(",");
                        for (int i = 0; i < produtosSplit.length; i++) {
                            String[] qtdSplit = produtosSplit[i].split("|");
                            int idProduto = Integer.parseInt(qtdSplit[0]);
                            ProdutoDto addProduto = pdao.getById(idProduto);
                            if(addProduto != null) {
                                this.pedido.setComposicao(addProduto);
                            }
                        }
                    }
                    pedidosObj.add((Object)this.pedido);
                } while (rs.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return pedidosObj;
    }
}
