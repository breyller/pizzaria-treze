package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.Clientedao;
import br.com.pizzariatreze.dao.Funcionariodao;
import br.com.pizzariatreze.dto.Clientedto;
import br.com.pizzariatreze.dto.Funcionariodto;
import br.com.pizzariatreze.util.Util;
import java.util.Map;
import java.util.ArrayList;

public class Funcionario extends Pessoa {

    public Object getById(int id) {
        Funcionariodto result = null;
        Funcionariodao funcionarioDao = new Funcionariodao();
        
        result = funcionarioDao.getById(id);
        return result;
    }
    
    public Object getByNome(String nome) {
        ArrayList<Funcionariodto> result = null;
        Funcionariodao funcionarioDao = new Funcionariodao();
        
        result = funcionarioDao.getByNome(nome);
        return result;
    }

    public Object getByTelefone(String telefone) {
        ArrayList<Funcionariodto> result = null;
        Funcionariodao funcionarioDao = new Funcionariodao();
        
        result = funcionarioDao.getByTelefone(telefone);
        return result;
    }
    
    public String save(Map funcionario) {
        String result = null;
        Funcionariodao funcionarioDao = new Funcionariodao();
                
        int idFuncionario = (int)funcionario.get("id");
        String nomeFuncionario = (String)funcionario.get("nome");
        String enderecoFuncionario = (String)funcionario.get("endereco");
        String telefoneFuncionario = (String)funcionario.get("telefone");
        String cpfFuncionario = (String)funcionario.get("cpf");
        double salarioFuncionario = (double)funcionario.get("salario");
        String cargoFuncionario = (String)funcionario.get("cargo");
        
        Funcionariodto funcionarioDto = new Funcionariodto(idFuncionario,nomeFuncionario,enderecoFuncionario,telefoneFuncionario,cpfFuncionario,salarioFuncionario,cargoFuncionario);
        
        result = funcionarioDao.save(funcionarioDto);
        return result;
    }
    
    public boolean cadastrarCliente(Clientedto cli) {
        boolean result = false;
        String tentativa = null;
        Clientedao clienteDao = new Clientedao(); 
        
        try {
            tentativa = clienteDao.save(cli);
            if (tentativa == "Cliente criado com sucesso." || tentativa == "Cliente atualizado com sucesso.") {
                result = true;
            }
        } catch(Exception e) {
            result = false;
        }
        
        return result;
    }

    public boolean login(String usuario, String senha) {
        
        Funcionariodto funcionario = new Funcionariodto();
        
        String senhaMD5 = Util.criptografar(senha);
        funcionario.setCpf(usuario);
        funcionario.setSenha(senhaMD5);
        
        Funcionariodao func = new Funcionariodao();
        
        if(!func.search(funcionario).isEmpty())
            return true;
        
        return false;
    }

}
