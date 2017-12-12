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
    
    public boolean save(Map funcionario) throws Exception{
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        FuncionarioDto funcionarioDto = new FuncionarioDto();

        if(funcionario.containsKey("id")) {
            funcionarioDto.setId(Integer.parseInt((String) funcionario.get("id")));
        }
        
        if(funcionario.containsKey("nome")) {
            if (funcionario.get("nome").toString().trim().isEmpty()) {
                throw new Exception("Nome deve estar preenchido.");
            }
            funcionarioDto.setNome(funcionario.get("nome").toString().trim());
        }
        
        if(funcionario.containsKey("endereco") && !funcionario.get("endereco").toString().trim().isEmpty()) {
            funcionarioDto.setEndereco(funcionario.get("endereco").toString().trim());
        }
        
        if(funcionario.containsKey("telefone") && funcionario.get("telefone").toString().trim().replace(" ", "").length() >= 13) {
            funcionarioDto.setTelefone(funcionario.get("telefone").toString().trim());
        }
        
        if(funcionario.containsKey("cpf")) {
            if (funcionario.get("cpf").toString().replaceAll("[\\.\\-]", "").replace(" ", "").length() < 11) {
                throw new Exception("CPF deve estar preenchido.");
            }
            funcionarioDto.setCpf(funcionario.get("cpf").toString().replaceAll("[\\.\\-]", ""));
        }
        
        if(funcionario.containsKey("salario")) {
            if (funcionario.get("salario").toString().trim().isEmpty()) {
                throw new Exception("Salario deve estar preenchido.");
            }
            
            double salario;

            try {
                salario = Double.parseDouble(funcionario.get("salario").toString().trim());
            } catch (Exception e) {
                throw new Exception("Salario deve ser um valor numérico.");
            }
            
            funcionarioDto.setSalario(salario);
        }
        
        if(funcionario.containsKey("cargo")) {
            if (funcionario.get("cargo").toString().trim().isEmpty()) {
                throw new Exception("Cargo deve estar preenchido.");
            }
            funcionarioDto.setCargo(funcionario.get("cargo").toString().trim());
        }
        
        if(funcionario.containsKey("senha")) {
            if (funcionario.get("senha").toString().trim().isEmpty()) {
                throw new Exception("Senha deve estar preenchido.");
            }
            funcionarioDto.setSenha(funcionario.get("senha").toString().trim());
        }
        
        return funcionarioDao.save(funcionarioDto);
    } 
    
    public boolean cadastrarFuncionario(FuncionarioDto cli) {
        FuncionarioDao funcionarioDao = new FuncionarioDao(); 
        return funcionarioDao.save(cli);
    }

    public boolean login(String usuario, char[] senha) {
        
        FuncionarioDto funcionario = new FuncionarioDto();
        List <Object> lista;
        char[] chars = senha;
        String password = String.valueOf(chars);
        String cpfLimpo = usuario.replaceAll("[\\.\\-]", "");
        String senhaCript = Util.criptografar(password);
        funcionario.setCpf(cpfLimpo);
        funcionario.setSenha(senhaCript);
        
        FuncionarioDao func = new FuncionarioDao();
        lista = func.search(funcionario);
        if(!lista.isEmpty()) {
            for (Object f : lista) {
                FuncionarioDto funcionarioBD = new FuncionarioDto();
                funcionarioBD = (FuncionarioDto) f;
                if(funcionarioBD.getCpf().replaceAll("[\\.\\-]", "").equals(cpfLimpo) && funcionarioBD.getSenha().equals(senhaCript)) {
                    System.setProperty("id_usuario_logado", String.valueOf(funcionarioBD.getId()));
                    return true;
                }
            }
        }
        
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

    public boolean delete(int id) {
        FuncionarioDao fdao = new FuncionarioDao();
        return fdao.delete(id);
    }
}
