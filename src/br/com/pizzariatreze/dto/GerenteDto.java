package br.com.pizzariatreze.dto;

public class GerenteDto extends FuncionarioDto{
    
    public GerenteDto(){
    }

    public GerenteDto(int id, String nome, String endereco, String telefone, String cpf, double salario){
        super(id,nome,endereco,telefone,cpf,salario,"Gerente");
    }
}
