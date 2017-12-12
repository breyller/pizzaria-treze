package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.PedidoDao;
import br.com.pizzariatreze.dto.ClienteDto;
import br.com.pizzariatreze.dto.FuncionarioDto;
import br.com.pizzariatreze.dto.PedidoDto;
import java.util.List;
import java.util.Map;

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

    public boolean save(Map pedido) throws Exception {
        PedidoDao pedidoDao = new PedidoDao();
        PedidoDto pedidoDto = new PedidoDto();

        if (pedido.containsKey("id")) { 
            pedidoDto.setId(Integer.parseInt(String.valueOf(pedido.get("id"))));
        }
        
        if (pedido.containsKey("cliente")) {
            if (pedido.get("cliente").toString().trim().isEmpty()) {
                throw new Exception("Cliente deve estar preenchido.");
            }
            pedidoDto.setCliente((ClienteDto)pedido.get("cliente"));
        }

        if (pedido.containsKey("composicao")) {
            if (pedido.get("composicao").toString().trim().isEmpty()) {
                throw new Exception("Composicao deve estar preenchido.");
            }
            pedidoDto.setComposicao(pedido.get("composicao").toString().trim());
        }
        
        if (pedido.containsKey("data")) {
            if (pedido.get("data").toString().trim().isEmpty()) {
                throw new Exception("Telefone deve estar corretamente preenchido.");
            }
            pedidoDto.setData(pedido.get("data").toString().trim());
        }
        
        if(pedido.containsKey("descricao")) {
            pedidoDto.setDescricao(pedido.get("descricao").toString());
        }
              
        if(pedido.containsKey("funcionario")) {
            pedidoDto.setFuncionario((FuncionarioDto)pedido.get("funcionario"));
        }
        
        if (pedido.containsKey("preco")) {
            if (pedido.get("preco").toString().trim().isEmpty()) {
                throw new Exception("Preço deve estar corretamente preenchido.");
            }
            pedidoDto.setPreco((Double)pedido.get("preco"));
        }
            
        if (pedido.containsKey("status")) {
            if (pedido.get("status").toString().trim().isEmpty()) {
                throw new Exception("Status deve estar corretamente preenchido.");
            }
            pedidoDto.setStatus(Integer.parseInt(String.valueOf(pedido.get("status"))));
        }
                
        if (pedido.containsKey("tipo")) {
            if (pedido.get("tipo").toString().trim().isEmpty()) {
                throw new Exception("Tipo deve estar corretamente preenchido.");
            }
            pedidoDto.setTipo((Integer)pedido.get("tipo"));
        }
        
        return pedidoDao.save(pedidoDto);
    }    

    public List<Object> listar() {
        PedidoDto pedido = new PedidoDto();
        
        PedidoDao pdao = new PedidoDao();
        List<Object> lista;
        lista = pdao.search(pedido);
        
        return lista;    
    }

    public List<Object> listar(int id) {
        PedidoDto pedido = new PedidoDto();
        
        if(id > 0) {
            pedido.setId(id);
        }
        
        PedidoDao pdao = new PedidoDao();
        List<Object> lista;
        lista = pdao.search(pedido);
        
        return lista;
    }
}
