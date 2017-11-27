package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.IngredienteDao;
import br.com.pizzariatreze.dto.IngredienteDto;
import java.util.Map;

public class Ingrediente {

    public boolean save(Map ingrediente) {
        IngredienteDao ingredienteDao = new IngredienteDao();
        IngredienteDto ingredienteDto = new IngredienteDto();

        if(ingrediente.containsKey("id")) ingredienteDto.setId((int)ingrediente.get("id"));
        if(ingrediente.containsKey("nome")) ingredienteDto.setNome((String)ingrediente.get("nome"));
        if(ingrediente.containsKey("descricao")) ingredienteDto.setDescricao((String)ingrediente.get("descricao"));
        if(ingrediente.containsKey("valor")) ingredienteDto.setValor((double)ingrediente.get("valor"));
        
        return ingredienteDao.save(ingredienteDto);
    } 
    
}
