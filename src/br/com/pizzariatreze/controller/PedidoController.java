package br.com.pizzariatreze.controller;

import br.com.pizzariatreze.model.Pedido;
import java.util.List;
import java.util.Map;

public class PedidoController {
    
    public boolean save(Map mapPedido) throws Exception {
        Pedido pedido = new Pedido();
        return pedido.save(mapPedido);
    }

    public List<Object> listar(int id) {
        Pedido pedido = new Pedido();
        return pedido.listar(id);
    }
    
    public List<Object> listar() {
        Pedido pedido = new Pedido();
        return pedido.listar();
    }
}
