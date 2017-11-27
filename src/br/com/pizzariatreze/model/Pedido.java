package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.PedidoDao;
import br.com.pizzariatreze.dto.PedidoDto;

public class Pedido {
    
    public boolean finalizarPedido(PedidoDto pedido) {
        PedidoDao pedDao = new PedidoDao();
        pedido.setStatus(1);
        return pedDao.save(pedido);
    }

    public boolean cancelarPedido(PedidoDto pedido) {
        PedidoDao pedDao = new PedidoDao();
        try {
            if (pedido.getStatus() < 3) {
                pedido.setStatus(-1);
                return pedDao.save(pedido);
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
