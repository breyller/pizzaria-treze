package model;

import DAO.FuncionarioDAO;

public class Funcionario extends Pessoa {
    private double salario;
    private String cargo;

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

}
