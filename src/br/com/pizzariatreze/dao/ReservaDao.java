package br.com.pizzariatreze.dao;

import br.com.pizzariatreze.bd.Conexao;
import br.com.pizzariatreze.dto.ReservaDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaDao {    
    private ArrayList<ReservaDto> reservas = null;
    private ReservaDto reserva = null;
    private Connection con = null;
    
    public ReservaDto getById(int id) {
        this.reserva = null;
        PreparedStatement ps = null;
        String mesas = null;
        String[] mesasSplit = null;
        MesaDao mesaDao = new MesaDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM reserva WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.reserva = new ReservaDto();
                this.reserva.setId(rs.getInt("id"));
                this.reserva.setNome(rs.getString("nome"));
                this.reserva.setStatus(rs.getInt("status"));
                this.reserva.setData(rs.getString("data"));
                mesas = rs.getString("composicao");
                mesasSplit = mesas.split(",");
                for (int i = 0; i < mesasSplit.length; i++) {
                    this.reserva.setComposicao(mesaDao.getById(Integer.parseInt(mesasSplit[i])));
                }
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
    
    public ArrayList<ReservaDto> getByNome(String nome) {
        this.reservas.clear();
        this.reserva = null;
        PreparedStatement ps = null;
        String mesas = null;
        String[] mesasSplit = null;
        MesaDao mesaDao = new MesaDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM reserva WHERE nome LIKE %'?'%");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.reserva = new ReservaDto();
                this.reserva.setId(rs.getInt("id"));
                this.reserva.setNome(rs.getString("nome"));
                this.reserva.setStatus(rs.getInt("status"));
                this.reserva.setData(rs.getString("data"));
                mesas = rs.getString("composicao");
                mesasSplit = mesas.split(",");
                for (int i = 0; i < mesasSplit.length; i++) {
                    this.reserva.setComposicao(mesaDao.getById(Integer.parseInt(mesasSplit[i])));
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

    public ArrayList<ReservaDto> getByStatus(int status) {
        this.reservas.clear();
        this.reserva = null;
        PreparedStatement ps = null;
        String mesas = null;
        String[] mesasSplit = null;
        MesaDao mesaDao = new MesaDao();
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM reserva WHERE status = ?");
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.reserva = new ReservaDto();
                this.reserva.setId(rs.getInt("id"));
                this.reserva.setNome(rs.getString("nome"));
                this.reserva.setStatus(rs.getInt("status"));
                this.reserva.setData(rs.getString("data"));
                mesas = rs.getString("composicao");
                mesasSplit = mesas.split(",");
                for (int i = 0; i < mesasSplit.length; i++) {
                    this.reserva.setComposicao(mesaDao.getById(Integer.parseInt(mesasSplit[i])));
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
    
    public boolean save(ReservaDto reserva) {
        String result = "Erro ao inserir/atualizar a reserva";
        String query = null;
        PreparedStatement ps = null;
        String mesas = "";
        
        if(reserva.getId() != 0) {
            ReservaDto reservaBD = this.getById(reserva.getId());
            if(reservaBD != null) {
                query = "UPDATE reserva SET nome = ?, status = ?, data = ?, composicao = ? WHERE id = ?";
                try {
                    ps = Conexao.getConexao().prepareStatement(query);
                    ps.setString(1, reserva.getNome());
                    ps.setInt(2, reserva.getStatus());
                    ps.setString(3, reserva.getData());
                    mesas = String.valueOf(reserva.getComposicao().get(0).getId());
                    for(int i = 1; i < reserva.getComposicao().size(); i++) {
                        mesas = mesas + "," + String.valueOf(reserva.getComposicao().get(i).getId());
                    }
                    ps.setString(4, mesas);
                    ps.setInt(5, reserva.getId());
                    ps.executeUpdate();
                    
                    return true;
                } catch (SQLException ex) {
                    return false;
                }
            }
        }
        
        try {
            query = reserva.getId() != 0 ? "INSERT INTO reserva(nome, status, data, composicao, id) VALUES (?,?,?,?,?)" : "INSERT INTO reserva(nome, status, data, composicao) VALUES (?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setString(1, reserva.getNome());
            ps.setInt(2, reserva.getStatus());
            ps.setString(3, reserva.getData());
            
            if(reserva.getComposicao() != null) {
                mesas = String.valueOf(reserva.getComposicao().get(0).getId());
                for(int i = 1; i < reserva.getComposicao().size(); i++) {
                    mesas = mesas + "," + String.valueOf(reserva.getComposicao().get(i).getId());
                }
            }else if(!reserva.getComposicaoString().equals("")){
                mesas = reserva.getComposicaoString();
            }
            
            ps.setString(4, mesas);
            if(reserva.getId() != 0) {
                ps.setInt(5, reserva.getId());
            }
            ps.executeUpdate();
            result = "Reserva criada com sucesso.";
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }
}
