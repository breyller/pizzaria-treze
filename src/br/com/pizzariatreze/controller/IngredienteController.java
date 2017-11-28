package br.com.pizzariatreze.controller;

import br.com.pizzariatreze.model.Ingrediente;
import java.util.List;
import java.util.Map;

public class IngredienteController {

        public boolean save(Map mapIngrediente) {
        Ingrediente ingrediente = new Ingrediente();
        return ingrediente.save(mapIngrediente);
    }

     public List<Object> listar() {
        Ingrediente ingrediente = new Ingrediente();
        return ingrediente.listar();
    } 
    
}
