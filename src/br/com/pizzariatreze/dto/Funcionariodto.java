package br.com.pizzariatreze.dto;

public class Funcionariodto extends Pessoadto {

    private double salario;
    private String cargo;
    
    public Funcionariodto(){
    }

    public Funcionariodto(int id, String nome, String endereco, String telefone, String cpf, double salario, String cargo){
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
