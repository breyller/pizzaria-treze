package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.ClienteDao;
import br.com.pizzariatreze.dto.ClienteDto;
import java.util.List;
import java.util.Map;

public class Cliente extends Pessoa {
    
    public Object getById(int id) {
        ClienteDto result = null;
        ClienteDao clienteDao = new ClienteDao();
        
        result = clienteDao.getById(id);
        return result;
    }
    
    public boolean save(Map cliente) throws Exception {
        ClienteDao clienteDao = new ClienteDao();
        ClienteDto clienteDto = new ClienteDto();

        if (cliente.containsKey("id")) { 
            clienteDto.setId(Integer.parseInt((String) cliente.get("id")));
        }
        
        if (cliente.containsKey("nome")) {
            if (cliente.get("nome").toString().trim().isEmpty()) {
                throw new Exception("Nome deve estar preenchido.");
            }
            clienteDto.setNome(cliente.get("nome").toString().trim()); 
        }
        
        if (cliente.containsKey("endereco")) {
            if (cliente.get("endereco").toString().trim().isEmpty()) {
                throw new Exception("Endereco deve estar preenchido.");
            }
            clienteDto.setEndereco(cliente.get("endereco").toString().trim());
        }
        
        if (cliente.containsKey("telefone")) {
            if (cliente.get("telefone").toString().trim().replace(" ", "").length() < 13) {
                throw new Exception("Telefone deve estar corretamente preenchido.");
            }
            clienteDto.setTelefone(cliente.get("telefone").toString().trim());
        }
        
        if(cliente.containsKey("cpf")) {
            if (cliente.get("cpf").toString().replaceAll("[\\.\\-]", "").replace(" ", "").length() < 11) {
                throw new Exception("CPF deve estar preenchido.");
            }
            clienteDto.setCpf(cliente.get("cpf").toString().replaceAll("[\\.\\-]", ""));
        }
 
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
    
    public List<Object> listar() {
        /* Criação do modelo */
            ClienteDto cliente = new ClienteDto();
 
            /* Criação do DAO */
            ClienteDao ddao = new ClienteDao();
            List<Object> lista;
            lista = ddao.search(cliente);
            
            return lista;    
    }

    public boolean delete(int id) {
        ClienteDao cdao = new ClienteDao();
        return cdao.delete(id);
    }
}
