package br.com.pizzariatreze.controller;

import br.com.pizzariatreze.model.Ingrediente;
import java.util.List;
import java.util.Map;

public class IngredienteController {

    public boolean save(Map mapIngrediente) throws Exception{
        Ingrediente ingrediente = new Ingrediente();
        return ingrediente.save(mapIngrediente);
    }

     public List<Object> listar() {
        Ingrediente ingrediente = new Ingrediente();
        return ingrediente.listar();
    } 

    public List<Object> listar(String id) {
        Ingrediente ingrediente = new Ingrediente();
        return ingrediente.listar(id);
    }

    public boolean delete(int id) {
        Ingrediente ingrediente = new Ingrediente();
        return ingrediente.delete(id);
    }
    
}
