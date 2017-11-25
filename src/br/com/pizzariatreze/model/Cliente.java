package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.ClienteDao;
import br.com.pizzariatreze.dto.ClienteDto;
import java.util.ArrayList;
import java.util.Map;

public class Cliente extends Pessoa {
    
    public Object getByNome(String nome) {
        ArrayList<ClienteDto> result = null;
        ClienteDao clienteDao = new ClienteDao();
        
        result = clienteDao.getByNome(nome);
        return result;
    }

    public Object getById(int id) {
        ClienteDto result = null;
        ClienteDao clienteDao = new ClienteDao();
        
        result = clienteDao.getById(id);
        return result;
    }
    
    public String save(Map cliente) {
        String result = null;
        ClienteDao clienteDao = new ClienteDao();

        int idCliente = (int)cliente.get("id");
        String nomeCliente = (String)cliente.get("nome");
        String enderecoCliente = (String)cliente.get("endereco");
        String telefoneCliente = (String)cliente.get("telefone");
        String cpfCliente = (String)cliente.get("cpf");
        
        ClienteDto clienteDto = new ClienteDto(idCliente,nomeCliente,enderecoCliente,telefoneCliente,cpfCliente);
        
        result = clienteDao.save(clienteDto);
        return result;
    }    
}
