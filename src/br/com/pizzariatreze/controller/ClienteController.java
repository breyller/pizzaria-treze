package br.com.pizzariatreze.controller;

import br.com.pizzariatreze.model.*;
import java.util.List;
import java.util.Map;

public class ClienteController {

    public boolean save(Map mapCliente) throws Exception {
        Cliente cliente = new Cliente();
        return cliente.save(mapCliente);
    }

    public List<Object> listar(String telefone) {
        Cliente cliente = new Cliente();
        return cliente.listar(telefone);
    }
    
    public List<Object> listar() {
        Cliente cliente = new Cliente();
        return cliente.listar();
    }    

    public boolean delete(int id) {
        Cliente cliente = new Cliente();
        return cliente.delete(id);
    }
}
