package br.com.pizzariatreze.controller;

import br.com.pizzariatreze.model.Ingrediente;
import java.util.Map;

public class IngredienteController {

        public boolean save(Map mapIngrediente) {
        Ingrediente ingrediente = new Ingrediente();
        return ingrediente.save(mapIngrediente);
    }
    
}
