package br.com.pizzariatreze.dto;

public class Gerentedto extends Funcionariodto{
    
    public Gerentedto(){
    }

    public Gerentedto(int id, String nome, String endereco, String telefone, String cpf, double salario){
        super(id,nome,endereco,telefone,cpf,salario,"Gerente");
    }
}
