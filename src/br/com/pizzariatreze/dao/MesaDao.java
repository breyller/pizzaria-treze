package br.com.pizzariatreze.dao;

import br.com.pizzariatreze.bd.Conexao;
import br.com.pizzariatreze.dto.Mesadto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MesaDao {

    private ArrayList<Mesadto> mesas = null;
    private Mesadto mesa = null;
    private Connection con = null;
    
    public Mesadto getById(int id) {
        this.mesa = null;
        PreparedStatement ps = null;
        String reservas = null;
        String[] reservasSplit = null;

        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM mesa WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.mesa = new Mesadto();
                this.mesa.setId(rs.getInt("id"));
                this.mesa.setNumero(rs.getInt("numero"));
                this.mesa.setQtdLugares(rs.getInt("qtd_lugares"));
                reservas = rs.getString("reservas");
                reservasSplit = reservas.split(",");
                for (int i = 0; i < reservasSplit.length; i++) {
                    this.mesa.setCodReserva(Integer.parseInt(reservasSplit[i]));
                }
                return this.mesa;
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
    
    public Mesadto getByNumero(int numero) {
        this.mesa = null;
        PreparedStatement ps = null;
        String reservas = null;
        String[] reservasSplit = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM mesa WHERE numero = ?");
            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                this.mesa = new Mesadto();
                this.mesa.setId(rs.getInt("id"));
                this.mesa.setNumero(rs.getInt("numero"));
                this.mesa.setQtdLugares(rs.getInt("qtd_lugares"));
                reservas = rs.getString("reservas");
                reservasSplit = reservas.split(",");
                for (int i = 0; i < reservasSplit.length; i++) {
                    this.mesa.setCodReserva(Integer.parseInt(reservasSplit[i]));
                }
                return this.mesa;
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

    public ArrayList<Mesadto> getByQtdLugares(int qtd) {
        this.mesas.clear();
        this.mesa = null;
        PreparedStatement ps = null;
        String reservas = null;
        String[] reservasSplit = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM mesa WHERE qtd_lugares = ?");
            ps.setInt(1, qtd);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.mesa = new Mesadto();
                this.mesa.setId(rs.getInt("id"));
                this.mesa.setNumero(rs.getInt("numero"));
                this.mesa.setQtdLugares(rs.getInt("qtd_lugares"));
                reservas = rs.getString("reservas");
                reservasSplit = reservas.split(",");
                for (int i = 0; i < reservasSplit.length; i++) {
                    this.mesa.setCodReserva(Integer.parseInt(reservasSplit[i]));
                }
                this.mesas.add(this.mesa);
                return this.mesas;
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
    
    public ArrayList<Mesadto> getByCodReserva(int codigo) {
        this.mesas.clear();
        this.mesa = null;
        PreparedStatement ps = null;
        String reservas = null;
        String[] reservasSplit = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM mesa WHERE reservas LIKE %'?'%");
            ps.setString(1, String.valueOf(codigo));
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.mesa = new Mesadto();
                this.mesa.setId(rs.getInt("id"));
                this.mesa.setNumero(rs.getInt("numero"));
                this.mesa.setQtdLugares(rs.getInt("qtd_lugares"));
                reservas = rs.getString("reservas");
                reservasSplit = reservas.split(",");
                for (int i = 0; i < reservasSplit.length; i++) {
                    this.mesa.setCodReserva(Integer.parseInt(reservasSplit[i]));
                }
                this.mesas.add(this.mesa);
                return this.mesas;
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
    
    public String save(Mesadto mesa) {
        String result = "Erro ao inserir/atualizar o cliente";
        String query = null;
        PreparedStatement ps = null;
        String reservas = null;
        
        if(mesa.getId() != 0) {
            Mesadto mesaBD = this.getById(mesa.getId());
            if(mesaBD != null) {
                query = "UPDATE mesa SET numero = ?, qtd_lugares = ?, reservas = ? WHERE id = ?";
                try {
                    ps = Conexao.getConexao().prepareStatement(query);
                    ps.setInt(1, mesa.getNumero());
                    ps.setInt(2, mesa.getQtdLugares());
                    reservas = String.valueOf(mesa.getCodReserva().get(0));
                    for(int i = 1; i < mesa.getCodReserva().size(); i++) {
                        reservas = reservas + "," + String.valueOf(mesa.getCodReserva().get(i));
                    }
                    ps.setString(3, reservas);
                    ps.setInt(4, mesa.getId());
                    ps.executeUpdate();
                    
                    return "Mesa atualizada com sucesso.";
                } catch (SQLException ex) {
                    return "Erro ao atualizar mesa: " + ex.getMessage();
                }
            }
        }
        
        try {
            query = mesa.getId() != 0 ? "INSERT INTO mesa(numero, qtd_lugares, reservas, id) VALUES (?,?,?,?)" : "INSERT INTO cliente(numero, qtd_lugares, reservas) VALUES (?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setInt(1, mesa.getNumero());
            ps.setInt(2, mesa.getQtdLugares());
            reservas = String.valueOf(mesa.getCodReserva().get(0));
            for(int i = 1; i < mesa.getCodReserva().size(); i++) {
                reservas = reservas + "," + String.valueOf(mesa.getCodReserva().get(i));
            }
            ps.setString(3, reservas);
            if(mesa.getId() != 0) {
                ps.setInt(4, mesa.getId());
            }
            ps.executeUpdate();
            result = "Mesa criada com sucesso.";
        } catch (SQLException ex) {
            return "Erro ao inserir mesa: " + ex.getMessage();
        }
        
        return result;
    }
}
