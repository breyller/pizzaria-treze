package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.MesaDao;
import br.com.pizzariatreze.dto.MesaDto;
import java.util.List;
import java.util.Map;

public class Mesa {

    public List<Object> listar(int numero) {
        /* Criação do modelo */
            MesaDto mesa = new MesaDto();
            
            if(numero > 0)
                mesa.setNumero(numero);

            /* Criação do DAO */
            MesaDao ddao = new MesaDao();
            List<Object> lista;
            lista = ddao.search(mesa);
            
            return lista;
            
    }
    
    public List<Object> listar() {
        /* Criação do modelo */
            MesaDto mesa = new MesaDto();
 
            /* Criação do DAO */
            MesaDao ddao = new MesaDao();
            List<Object> lista;
            lista = ddao.search(mesa);
            
            return lista;    
    }

    public boolean delete(int id) {
        MesaDao mdao = new MesaDao();
        return mdao.delete(id);
    }

    public List<Object> listarMesasLivres() {
            MesaDao ddao = new MesaDao();
            List<Object> lista;
            lista = ddao.listarMesasLivres();
            
            return lista; 
    }

    public boolean save(Map mesa) throws Exception{
        MesaDao mdao = new MesaDao();
        MesaDto mesaDto = new MesaDto();

        if(mesa.containsKey("id")) {
            mesaDto.setId(Integer.parseInt((String) mesa.get("id")));
        }
        
        if(mesa.containsKey("numero")) {
            if (mesa.get("numero").toString().trim().isEmpty()) {
                throw new Exception("Numero da mesa deve estar preenchido.");
            }
            mesaDto.setNumero(Integer.parseInt(mesa.get("numero").toString().trim()));
        }
        
        if(mesa.containsKey("qtd_lugares")) {
            if (mesa.get("qtd_lugares").toString().trim().isEmpty()) {
                throw new Exception("Quantidade de lugares deve estar preenchida.");
            }
            mesaDto.setQtdLugares(Integer.parseInt(mesa.get("qtd_lugares").toString().trim()));
        }
                
        return mdao.save(mesaDto);
    }
}
