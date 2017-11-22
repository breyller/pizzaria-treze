package br.com.pizzariatreze.model;

import br.com.pizzariatreze.DAO.FuncionarioDAO;
import java.util.ArrayList;

public class Funcionario extends Pessoa {
    private double salario;
    private String cargo;
    
    public Funcionario(){
        return;
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
    
    public Funcionario getById(int id) {
        Funcionario result = null;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        
        result = funcionarioDao.getById(id);
        return result;
    }
    
    public ArrayList<Funcionario> getByNome(String nome) {
        ArrayList<Funcionario> result = null;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        
        result = funcionarioDao.getByNome(nome);
        return result;
    }

    public ArrayList<Funcionario> getByTelefone(String telefone) {
        ArrayList<Funcionario> result = null;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        
        result = funcionarioDao.getByTelefone(telefone);
        return result;
    }
    
    public String save() {
        String result = null;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        
        result = funcionarioDao.save(this);
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

}
