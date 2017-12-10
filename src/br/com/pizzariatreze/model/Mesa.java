package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.MesaDao;
import br.com.pizzariatreze.dto.MesaDto;
import java.util.List;

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
}
