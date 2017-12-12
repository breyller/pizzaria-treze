package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.IngredienteDao;
import br.com.pizzariatreze.dto.IngredienteDto;
import java.util.List;
import java.util.Map;

public class Ingrediente {

    public boolean save(Map ingrediente) throws Exception{
        IngredienteDao ingredienteDao = new IngredienteDao();
        IngredienteDto ingredienteDto = new IngredienteDto();

        if(ingrediente.containsKey("id")) {
            ingredienteDto.setId(Integer.parseInt((String) ingrediente.get("id")));
        }
        
        if(ingrediente.containsKey("nome")) {
            if (ingrediente.get("nome").toString().trim().isEmpty()) {
                throw new Exception("Nome do ingrediente deve estar preenchido.");
            }
            ingredienteDto.setNome(ingrediente.get("nome").toString().trim());
        }
        
        if(ingrediente.containsKey("descricao")) {
            if (ingrediente.get("descricao").toString().trim().isEmpty()) {
                throw new Exception("Descricao deve estar preenchida.");
            }
            ingredienteDto.setDescricao(ingrediente.get("descricao").toString().trim());
        }
        
        if(ingrediente.containsKey("valor")) {
            if (ingrediente.get("valor").toString().trim().isEmpty()) {
                throw new Exception("Valor deve estar preenchido.");
            }
            
            double valor;
            
            try {
                valor = Double.parseDouble(ingrediente.get("valor").toString().trim());
            } catch (Exception e) {
                throw new Exception("Valor deve ser numérico.");
            }
                        
            ingredienteDto.setValor(valor);
        }
        
        if(ingrediente.containsKey("quantidade")) {
            if (ingrediente.get("quantidade").toString().trim().isEmpty()) {
                throw new Exception("Quantidade deve estar preenchido.");
            }
            
            int qtd;
            
            try {
                qtd = Integer.parseInt(ingrediente.get("quantidade").toString().trim());
            } catch (Exception e) {
                throw new Exception("Valor deve ser numérico.");
            }
            
            if(qtd <= 0) {
                throw new Exception("Quantidade deve ser um numero positivo.");
            }
            
            ingredienteDto.setQuantidade(qtd);
        }
        
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
    
        public List<Object> listar(String id) {
        /* Criação do modelo */
            IngredienteDto ingrediente = new IngredienteDto();
            
            if(id.length() > 0)
                ingrediente.setId(Integer.parseInt(id));

            /* Criação do DAO */
            IngredienteDao ddao = new IngredienteDao();
            List<Object> lista;
            lista = ddao.search(ingrediente);
            
            return lista;
            
    }

    public boolean delete(int id) {
        IngredienteDao idao = new IngredienteDao();
        return idao.delete(id);
    }

}
