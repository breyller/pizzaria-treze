package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.FuncionarioDao;
import br.com.pizzariatreze.dto.FuncionarioDto;
import br.com.pizzariatreze.util.Util;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

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
        FuncionarioDto funcionarioDto = new FuncionarioDto();

        if(funcionario.containsKey("id")) funcionarioDto.setId((int)funcionario.get("id"));
        if(funcionario.containsKey("nome")) funcionarioDto.setNome((String)funcionario.get("nome"));
        if(funcionario.containsKey("endereco")) funcionarioDto.setEndereco((String)funcionario.get("endereco"));
        if(funcionario.containsKey("telefone")) funcionarioDto.setTelefone((String)funcionario.get("telefone"));
        if(funcionario.containsKey("cpf")) funcionarioDto.setCpf(funcionario.get("cpf").toString().replaceAll("[\\.\\-]", ""));
        if(funcionario.containsKey("salario")) funcionarioDto.setSalario((double)funcionario.get("salario"));
        if(funcionario.containsKey("cargo")) funcionarioDto.setCargo((String)funcionario.get("cargo"));
 
        return funcionarioDao.save(funcionarioDto);
    } 
    
    public boolean cadastrarFuncionario(FuncionarioDto cli) {
        FuncionarioDao funcionarioDao = new FuncionarioDao(); 
        return funcionarioDao.save(cli);
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

    public List<Object> listar(int id) {
        /* Criação do modelo */
        FuncionarioDto funcionario = new FuncionarioDto();

        if(id > 0)
            funcionario.setId(id);

        /* Criação do DAO */
        FuncionarioDao ddao = new FuncionarioDao();
        List<Object> lista;
        lista = ddao.search(funcionario);

        return lista;
            
    }
    
    public List<Object> listar() {
        /* Criação do modelo */
        FuncionarioDto funcionario = new FuncionarioDto();

        /* Criação do DAO */
        FuncionarioDao ddao = new FuncionarioDao();
        List<Object> lista;
        lista = ddao.search(funcionario);

        return lista;
            
    }

}
