package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.ClienteDao;
import br.com.pizzariatreze.dto.ClienteDto;
import java.util.ArrayList;
import java.util.List;
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
    
    public boolean save(Map cliente) {
        ClienteDao clienteDao = new ClienteDao();
        ClienteDto clienteDto = new ClienteDto();

        if(cliente.containsKey("id")) clienteDto.setId((int)cliente.get("id"));
        if(cliente.containsKey("nome")) clienteDto.setNome((String)cliente.get("nome"));
        if(cliente.containsKey("endereco")) clienteDto.setEndereco((String)cliente.get("endereco"));
        if(cliente.containsKey("telefone")) clienteDto.setTelefone((String)cliente.get("telefone"));
        if(cliente.containsKey("cpf")) clienteDto.setCpf(cliente.get("cpf").toString().replaceAll("[\\.\\-]", ""));
 
        return clienteDao.save(clienteDto);
    }    

    public List<Object> listar(String telefone) {
        /* Criação do modelo */
            ClienteDto cliente = new ClienteDto();
            
            if(telefone.length() > 0)
                cliente.setTelefone(telefone);

            /* Criação do DAO */
            ClienteDao ddao = new ClienteDao();
            List<Object> lista;
            lista = ddao.search(cliente);
            
            return lista;
            
    }
}
