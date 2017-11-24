package br.com.pizzariatreze.model;

import br.com.pizzariatreze.DAO.FuncionarioDAO;
import br.com.pizzariatreze.DTO.FuncionarioDTO;
import java.util.Map;
import java.util.ArrayList;

public class Funcionario extends Pessoa {

    public Object getById(int id) {
        FuncionarioDTO result = null;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        
        result = funcionarioDao.getById(id);
        return result;
    }
    
    public Object getByNome(String nome) {
        ArrayList<FuncionarioDTO> result = null;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        
        result = funcionarioDao.getByNome(nome);
        return result;
    }

    public Object getByTelefone(String telefone) {
        ArrayList<FuncionarioDTO> result = null;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        
        result = funcionarioDao.getByTelefone(telefone);
        return result;
    }
    
    public String save(Map funcionario) {
        String result = null;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
                
        int idFuncionario = (int)funcionario.get("id");
        String nomeFuncionario = (String)funcionario.get("nome");
        String enderecoFuncionario = (String)funcionario.get("endereco");
        String telefoneFuncionario = (String)funcionario.get("telefone");
        String cpfFuncionario = (String)funcionario.get("cpf");
        double salarioFuncionario = (double)funcionario.get("salario");
        String cargoFuncionario = (String)funcionario.get("cargo");
        
        FuncionarioDTO funcionarioDto = new FuncionarioDTO(idFuncionario,nomeFuncionario,enderecoFuncionario,telefoneFuncionario,cpfFuncionario,salarioFuncionario,cargoFuncionario);
        
        result = funcionarioDao.save(funcionarioDto);
        return result;
    }
    
    public boolean cadastrarCliente(Cliente cli) {
        boolean result = false;
        String tentativa = null;
        
        try {
            tentativa = cli.save();
            if (tentativa == "Cliente criado com sucesso." || tentativa == "Cliente atualizado com sucesso.") {
                result = true;
            }
        } catch(Exception e) {
            result = false;
        }
        
        return result;
    }

    public boolean login(String usuario, String senha) {
        return false;
    }

}
