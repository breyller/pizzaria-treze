package br.com.pizzariatreze.dao;

import br.com.pizzariatreze.bd.Conexao;
import br.com.pizzariatreze.dto.MesaDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesaDao {

    private ArrayList<MesaDto> mesas = new ArrayList<>();
    private MesaDto mesa = null;
    private Connection con = null;
    
    public MesaDto getById(int id) {
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
                this.mesa = new MesaDto();
                this.mesa.setId(rs.getInt("id"));
                this.mesa.setNumero(rs.getInt("numero"));
                this.mesa.setQtdLugares(rs.getInt("qtd_lugares"));
                reservas = rs.getString("reservas");
                reservasSplit = reservas.split(",");
                if(!reservas.equals(null) && reservas.length() > 0) {
                    for (int i = 0; i < reservasSplit.length; i++) {
                        this.mesa.setCodReserva(Integer.parseInt(reservasSplit[i]));
                    }
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
    
    public MesaDto getByNumero(int numero) {
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
                this.mesa = new MesaDto();
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

    public ArrayList<MesaDto> getByQtdLugares(int qtd) {
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
                this.mesa = new MesaDto();
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
    
    public ArrayList<MesaDto> getByCodReserva(int codigo) {
        this.mesas.clear();
        this.mesa = null;
        PreparedStatement ps = null;
        String reservas = null;
        String[] reservasSplit = null;
        boolean codPresente = false;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM mesa WHERE reservas LIKE %'?'%");
            ps.setString(1, String.valueOf(codigo));
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.mesa = new MesaDto();
                this.mesa.setId(rs.getInt("id"));
                this.mesa.setNumero(rs.getInt("numero"));
                this.mesa.setQtdLugares(rs.getInt("qtd_lugares"));
                reservas = rs.getString("reservas");
                reservasSplit = reservas.split(",");
                for (int i = 0; i < reservasSplit.length; i++) {
                    this.mesa.setCodReserva(Integer.parseInt(reservasSplit[i]));
                }
                
                for (int j = 0; j < this.mesa.getCodReserva().size(); j++) {
                    if(this.mesa.getCodReserva().get(j) == codigo) {
                        codPresente = true;
                    }
                }
                
                if(codPresente) {
                    this.mesas.add(this.mesa);
                }
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
    
    public boolean save(MesaDto mesa) {
        String query = null;
        PreparedStatement ps = null;
        String reservas = null;
        
        if(mesa.getId() != 0) {
            MesaDto mesaBD = this.getById(mesa.getId());
            if(mesaBD != null) {
                query = "UPDATE mesa SET numero = ?, qtd_lugares = ?, status = ? WHERE id = ?";
                try {
                    ps = Conexao.getConexao().prepareStatement(query);
                    ps.setInt(1, mesa.getNumero());
                    ps.setInt(2, mesa.getQtdLugares());
                    ps.setInt(3, mesa.getStatus());
                    ps.setInt(4, mesa.getId());
                    ps.executeUpdate();
                    
                    return true;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        }
        
        try {
            query = "INSERT INTO mesa(numero, qtd_lugares, reservas, status) VALUES (?,?,?,?)";
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setInt(1, mesa.getNumero());
            ps.setInt(2, mesa.getQtdLugares());
            reservas = "";
            if(mesa.getCodReserva().size() > 0) {
                reservas = String.valueOf(mesa.getCodReserva().get(0));
                for(int i = 1; i < mesa.getCodReserva().size(); i++) {
                    reservas = reservas + "," + String.valueOf(mesa.getCodReserva().get(i));
                }
            }
            ps.setString(3, reservas);
            ps.setInt(4, mesa.getStatus());
            ps.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<Object> search(MesaDto mesa) {
        
        List<Object> mesasObj = new ArrayList<>();
        this.mesa = null;

        try{
            con = Conexao.getConexao();
            String sql = "SELECT * FROM mesa";
            PreparedStatement ps = null;
        
            List parametros = new ArrayList<>();
            
            if(mesa.getId() > 0){
                sql += " WHERE ID = ? ";
                parametros.add(mesa.getId());
            }else{
                List alt = mesa.getAlterado();
                String sqlWhere = "";
                int index = 1;

                if(alt.contains("numero")) {
                    sqlWhere += " AND numero = ? ";
                    parametros.add(mesa.getNumero());
                }

                if(alt.contains("qtd_lugares")){
                    sqlWhere += " AND qtd_lugares = ? ";
                    parametros.add(mesa.getQtdLugares());
                }

                if(alt.contains("status")){
                    sqlWhere += " AND status = ? ";
                    parametros.add(mesa.getStatus());
                }

                if(alt.contains("reservas")){
                    sqlWhere += " AND reservas = ? ";
                    ArrayList<Integer> reservas = mesa.getCodReserva();
                    String strReservas = "";
                    for(int res : reservas){
                        strReservas += String.valueOf(res) + ",";
                    }

                    strReservas = strReservas.substring(-1);

                    parametros.add(strReservas);
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
                    this.mesa = new MesaDto();
                    this.mesa.setId(rs.getInt("id"));
                    this.mesa.setNumero(rs.getInt("numero"));
                    this.mesa.setQtdLugares(rs.getInt("qtd_lugares"));
                    String reservas = rs.getString("reservas");
                   /* 
                    if(reservas.length() > 1){
                        String[] reservasSplit = reservas.split(",");
                        for (int i = 0; i < reservasSplit.length; i++) {
                            this.mesa.setCodReserva(Integer.parseInt(reservasSplit[i]));
                        }
                    }*/
                    
                    mesasObj.add((Object)this.mesa);
                } while (rs.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return mesasObj;
    }

    public boolean delete(int id) {
        String query = null;
        PreparedStatement ps = null;
        
        query = "DELETE FROM mesa WHERE id = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Object> listarMesasLivres() {
        
        List<Object> mesasObj = new ArrayList<>();
        this.mesas.clear();
        this.mesa = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexao.getConexao();
            ps = con.prepareStatement("SELECT * FROM pizzaria.mesa where status = 0");
            ResultSet rs = ps.executeQuery();
            
            if(!rs.next()) {
                return null;
            }
            
            do {
                this.mesa = new MesaDto();
                this.mesa.setId(rs.getInt("id"));
                this.mesa.setNumero(rs.getInt("numero"));
                this.mesa.setQtdLugares(rs.getInt("qtd_lugares"));
                this.mesas.add(this.mesa);
                mesasObj.add(this.mesa);
            } while (rs.next());
            
            return mesasObj;
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
}
