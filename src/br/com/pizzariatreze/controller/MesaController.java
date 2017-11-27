package br.com.pizzariatreze.controller;

import br.com.pizzariatreze.model.Mesa;
import java.util.List;

public class MesaController {

    public List<Object> listar(int numero) {
        Mesa mesa = new Mesa();
        return mesa.listar(numero);
    }
    
    public List<Object> listar() {
        Mesa mesa = new Mesa();
        return mesa.listar();
    } 
    
}
