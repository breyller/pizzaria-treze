package br.com.pizzariatreze.model;

import java.util.ArrayList;
import br.com.pizzariatreze.DAO.FuncionarioDAO;
import br.com.pizzariatreze.DTO.FuncionarioDTO;
import java.util.List;
import java.util.Map;

public class Funcionario extends Pessoa {
    private double salario;
    private String cargo;
    
    public Funcionario(){
    }

    public Funcionario(int id, String nome, String endereco, String telefone, String cpf, double salario, String cargo){
        super(id,nome,endereco,telefone,cpf);
        this.setSalario(salario);
        this.setCargo(cargo);
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
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
        FuncionarioDTO funcionarioDto = new FuncionarioDTO();
        
        funcionarioDto.setNome((String) funcionario.get("nome"));
        
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
