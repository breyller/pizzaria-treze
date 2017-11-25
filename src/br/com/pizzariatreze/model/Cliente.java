package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.Clientedao;
import br.com.pizzariatreze.dto.Clientedto;
import java.util.ArrayList;
import java.util.Map;

public class Cliente extends Pessoa {
    
    public Object getByNome(String nome) {
        ArrayList<Clientedto> result = null;
        Clientedao clienteDao = new Clientedao();
        
        result = clienteDao.getByNome(nome);
        return result;
    }

    public Object getById(int id) {
        Clientedto result = null;
        Clientedao clienteDao = new Clientedao();
        
        result = clienteDao.getById(id);
        return result;
    }
    
    public String save(Map cliente) {
        String result = null;
        Clientedao clienteDao = new Clientedao();

        int idCliente = (int)cliente.get("id");
        String nomeCliente = (String)cliente.get("nome");
        String enderecoCliente = (String)cliente.get("endereco");
        String telefoneCliente = (String)cliente.get("telefone");
        String cpfCliente = (String)cliente.get("cpf");
        
        Clientedto clienteDto = new Clientedto(idCliente,nomeCliente,enderecoCliente,telefoneCliente,cpfCliente);
        
        result = clienteDao.save(clienteDto);
        return result;
    }    
}
