package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.ClienteDao;
import br.com.pizzariatreze.dao.FuncionarioDao;
import br.com.pizzariatreze.dto.ClienteDto;
import br.com.pizzariatreze.dto.FuncionarioDto;
import br.com.pizzariatreze.util.Util;
import java.util.Map;
import java.util.ArrayList;

public class Funcionario extends Pessoa {

    public Object getById(int id) {
        FuncionarioDto result = null;
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        
        result = funcionarioDao.getById(id);
        return result;
    }
    
    public Object getByNome(String nome) {
        ArrayList<FuncionarioDto> result = null;
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        
        result = funcionarioDao.getByNome(nome);
        return result;
    }

    public Object getByTelefone(String telefone) {
        ArrayList<FuncionarioDto> result = null;
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        
        result = funcionarioDao.getByTelefone(telefone);
        return result;
    }
    
    public boolean save(Map funcionario) {
        FuncionarioDao funcionarioDao = new FuncionarioDao();
                
        int idFuncionario = (int)funcionario.get("id");
        String nomeFuncionario = (String)funcionario.get("nome");
        String enderecoFuncionario = (String)funcionario.get("endereco");
        String telefoneFuncionario = (String)funcionario.get("telefone");
        String cpfFuncionario = (String)funcionario.get("cpf");
        double salarioFuncionario = (double)funcionario.get("salario");
        String cargoFuncionario = (String)funcionario.get("cargo");
        
        FuncionarioDto funcionarioDto = new FuncionarioDto(idFuncionario,nomeFuncionario,enderecoFuncionario,telefoneFuncionario,cpfFuncionario,salarioFuncionario,cargoFuncionario);
        
        return funcionarioDao.save(funcionarioDto);
    }
    
    public boolean cadastrarCliente(ClienteDto cli) {
        ClienteDao clienteDao = new ClienteDao(); 
        return clienteDao.save(cli);
    }

    public boolean login(String usuario, char[] senha) {
        
        FuncionarioDto funcionario = new FuncionarioDto();
        
        char[] chars = senha;
        String password = String.valueOf(chars);
        String cpfLimpo = usuario.replaceAll("[\\.\\-]", "");
        String senhaCript = Util.criptografar(password);
        funcionario.setCpf(cpfLimpo);
        funcionario.setSenha(senhaCript);
        
        FuncionarioDao func = new FuncionarioDao();
        
        if(!func.search(funcionario).isEmpty())
            return true;
        
        return false;
    }

}
