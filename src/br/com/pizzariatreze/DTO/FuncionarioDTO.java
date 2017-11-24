package br.com.pizzariatreze.DTO;

import br.com.pizzariatreze.DAO.FuncionarioDAO;
import java.util.Map;

public class FuncionarioDTO extends PessoaDTO {

    private double salario;
    private String cargo;
    
    public FuncionarioDTO(){
    }

    public FuncionarioDTO(int id, String nome, String endereco, String telefone, String cpf, double salario, String cargo){
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
}
