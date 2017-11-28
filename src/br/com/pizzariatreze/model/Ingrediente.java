package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.IngredienteDao;
import br.com.pizzariatreze.dto.IngredienteDto;
import java.util.List;
import java.util.Map;

public class Ingrediente {

    public boolean save(Map ingrediente) {
        IngredienteDao ingredienteDao = new IngredienteDao();
        IngredienteDto ingredienteDto = new IngredienteDto();

        if(ingrediente.containsKey("id")) ingredienteDto.setId((int)ingrediente.get("id"));
        if(ingrediente.containsKey("nome")) ingredienteDto.setNome((String)ingrediente.get("nome"));
        if(ingrediente.containsKey("descricao")) ingredienteDto.setDescricao((String)ingrediente.get("descricao"));
        if(ingrediente.containsKey("valor")) ingredienteDto.setValor(Double.parseDouble((String)ingrediente.get("valor")));
        if(ingrediente.containsKey("quantidade")) ingredienteDto.setQuantidade(Integer.parseInt((String)ingrediente.get("quantidade")));
        
        return ingredienteDao.save(ingredienteDto);
    } 
    
        public List<Object> listar() {
        /* Criação do modelo */
            IngredienteDto ingrediente = new IngredienteDto();
 
            /* Criação do DAO */
            IngredienteDao ddao = new IngredienteDao();
            List<Object> lista;
            lista = ddao.search(ingrediente);
            
            return lista;    
    }
    
}
