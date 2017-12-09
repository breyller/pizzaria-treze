/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzariatreze.controller;

import br.com.pizzariatreze.model.Pedido;
import java.util.Map;

/**
 *
 * @author Fabio
 */
public class PedidoController {
    
    public boolean save(Map mapPedido) throws Exception {
        Pedido pedido = new Pedido();
        return pedido.save(mapPedido);
    }
}
