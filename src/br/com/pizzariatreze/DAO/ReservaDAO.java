package br.com.pizzariatreze.DAO;

import br.com.pizzariatreze.BD.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.pizzariatreze.DTO.ReservaDTO;
import br.com.pizzariatreze.model.Mesa;
import br.com.pizzariatreze.DAO.MesaDAO;
import java.util.ArrayList;

public class ReservaDAO {    
    private ArrayList<ReservaDTO> reservas = null;
    private ReservaDTO reserva = null;
    private Connection con = null;
    
    public ReservaDAO getById(int id) {
        this.reserva = null;
        PreparedStatement ps = null;
        String mesas = null;
        String[] mesasSplit = null;
//        ArrayList<Mesa> composicao = null;
//        MesaDAO mesaDao = new MesaDAO();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM reserva WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.reserva = new ReservaDAO();
                this.reserva.setId(rs.getInt("id"));
                this.reserva.setNome(rs.getString("nome"));
                this.reserva.setStatus(rs.getInt("status"));
                this.reserva.setData(rs.getString("data"));
                mesas = rs.getString("composicao");
                mesasSplit = mesas.split(",");
                for (int i = 0; i < mesasSplit.length; i++) {
                    this.reserva.setComposicao(Integer.parseInt(mesasSplit[i]));
                }
//                for (int i = 0; i < mesasSplit.length; i++) {
//                    composicao.add(mesaDao.getById(Integer.parseInt(mesasSplit[i])));
//                }
                return this.reserva;
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
    
    public ArrayList<ReservaDAO> getByNome(String nome) {
        this.reservas.clear();
        this.reserva = null;
        PreparedStatement ps = null;
        String mesas = null;
        String[] mesasSplit = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM reserva WHERE nome LIKE %'?'%");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.reserva = new ReservaDAO();
                this.reserva.setId(rs.getInt("id"));
                this.reserva.setNome(rs.getString("nome"));
                this.reserva.setStatus(rs.getInt("status"));
                this.reserva.setData(rs.getString("data"));
                mesas = rs.getString("composicao");
                mesasSplit = mesas.split(",");
                for (int i = 0; i < mesasSplit.length; i++) {
                    this.reserva.setComposicao(Integer.parseInt(mesasSplit[i]));
                }
                this.reservas.add(this.reserva);
                return this.reservas;
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

    public ArrayList<ReservaDAO> getByStatus(int status) {
        this.reservas.clear();
        this.reserva = null;
        PreparedStatement ps = null;
        String mesas = null;
        String[] mesasSplit = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM reserva WHERE status = ?");
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.reserva = new ReservaDAO();
                this.reserva.setId(rs.getInt("id"));
                this.reserva.setNome(rs.getString("nome"));
                this.reserva.setStatus(rs.getInt("status"));
                this.reserva.setData(rs.getString("data"));
                mesas = rs.getString("composicao");
                mesasSplit = mesas.split(",");
                for (int i = 0; i < mesasSplit.length; i++) {
                    this.reserva.setComposicao(Integer.parseInt(mesasSplit[i]));
                }
                this.reservas.add(this.reserva);
                return this.reservas;
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
    
    public String save(ReservaDAO reserva) {
        String result = "Erro ao inserir/atualizar a reserva";
        String query = null;
        PreparedStatement ps = null;
        String mesas = null;
        
        if(reserva.getId() != 0) {
            ReservaDAO reservaBD = this.getById(reserva.getId());
            if(reservaBD != null) {
                query = "UPDATE reserva SET nome = ?, status = ?, data = ?, composicao = ? WHERE id = ?";
                try {
                    ps = Conexao.getConexao().prepareStatement(query);
                    ps.setString(1, reserva.getNome());
                    ps.setInt(2, reserva.getStatus());
                    ps.setString(3, reserva.getData());
                    mesas = String.valueOf(reserva.getComposicao().get(0));
                    for(int i = 1; i < reserva.getComposicao().size(); i++) {
                        mesas = mesas + "," + String.valueOf(reserva.getComposicao().get(i));
                    }
                    ps.setString(4, mesas);
                    ps.setInt(5, reserva.getId());
                    ps.executeUpdate();
                    
                    return "Reserva atualizada com sucesso.";
                } catch (SQLException ex) {
                    return "Erro ao atualizar reserva: " + ex.getMessage();
                }
            }
        }
        
        try {
            query = reserva.getId() != 0 ? "INSERT INTO reserva(nome, status, data, composicao, id) VALUES (?,?,?,?,?)" : "INSERT INTO reserva(nome, status, data, composicao) VALUES (?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, reserva.getNome());
            ps.setInt(2, reserva.getStatus());
            ps.setString(3, reserva.getData());
            mesas = String.valueOf(reserva.getComposicao().get(0));
            for(int i = 1; i < reserva.getComposicao().size(); i++) {
                mesas = mesas + "," + String.valueOf(reserva.getComposicao().get(i));
            }
            ps.setString(4, mesas);
            if(reserva.getId() != 0) {
                ps.setInt(5, reserva.getId());
            }
            ps.executeUpdate();
            result = "Reserva criada com sucesso.";
        } catch (SQLException ex) {
            return "Erro ao inserir reserva: " + ex.getMessage();
        }
        
        return result;
    }
}
