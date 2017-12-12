package br.com.pizzariatreze.controller;

import br.com.pizzariatreze.model.Mesa;
import java.util.List;
import java.util.Map;

public class MesaController {

    public List<Object> listar(int numero) {
        Mesa mesa = new Mesa();
        return mesa.listar(numero);
    }
    
    public List<Object> listar() {
        Mesa mesa = new Mesa();
        return mesa.listar();
    } 

    public boolean delete(int id) {
        Mesa mesa = new Mesa();
        return mesa.delete(id);
    }

    public List<Object> listarMesasLivres() {
        Mesa mesa = new Mesa();
        return mesa.listarMesasLivres();
    }

    public boolean save(Map mapMesa) throws Exception{
        Mesa mesa = new Mesa();
        return mesa.save(mapMesa);
    }
}
